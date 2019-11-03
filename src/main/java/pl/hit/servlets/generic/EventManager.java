package pl.hit.servlets.generic;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * Klasa EventManager zarządza wystąpieniami zdarzeń w aplikacji.
 * Dostęp do klasy odbywa się z wykorzystaniem metody statycznej getInstance().
 * Wykorzystanie klasy może być następujące:
 *
 * <code>
 * EventManager.getInstance().notify(EventType.REQUEST_STARTED);
 * </code>
 * <p>
 * Dostęp do mapy wystąpień możliwy jest z wykorzystaniem metody getOccurences().
 * Metoda ta zwraca kopie wewnętrznej struktury przechowującej wystąpienia.
 *
 * <code>
 * Map<EventType, Integer> occurences = EventManager.getInstance().getOccurences();
 * </code>
 */
public class EventManager {

    public static EventManager getInstance() {
        return InstanceWrapper.INSTANCE;
    }

    private ConcurrentHashMap<EventType, Integer> eventsOccurence = new ConcurrentHashMap<>();

    /**
     * Metoda służy do załadowania wcześniejszych wystąpień zdarzeń
     * z pliku.
     */
    public void load() {
        if (getStoreFile().exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new BufferedReader(new FileReader(getStoreFile())));
                for (String prop : properties.stringPropertyNames()) {
                    EventType eventType = EventType.valueOf(prop);
                    eventsOccurence.put(eventType,
                            Integer.parseInt(properties.getProperty(prop, "0")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            init();
        }
    }

    /**
     * Metoda służy do zapisu bieżących wystąpień do pliku konfiguracyjnego.
     */
    public void store() {
        Properties properties = new Properties();
        for (Map.Entry<EventType, Integer> entry : eventsOccurence.entrySet()) {
            properties.setProperty(entry.getKey().name(), entry.getValue().toString());
        }
        try {
            properties.store(new FileWriter(getStoreFile()), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda służy do powiadomienia o wystąpieniu danego zdarzenia.
     *
     * @param eventType typ zdarzenia, które wystąpiło
     */
    public void notify(EventType eventType) {
        eventsOccurence.compute(eventType, (type, integer) -> integer + 1);
        System.out.println("EventManager.notify Zdarzenie typu " + eventType
                + " w wątku [" + Thread.currentThread().getName() + "]");
    }

    /**
     * Metoda zwraca kopie mapy wystąpień zdarzeń.
     *
     * @return kopia mapy wystąpień zdarzeń.
     */
    public Map<EventType, Integer> getOccurences() {
        return new TreeMap<>(eventsOccurence);
    }

    private EventManager() {
        init();
    }

    private void init() {
        for (EventType eventType : EventType.values()) {
            eventsOccurence.put(eventType, 0);
        }
    }

    private File getStoreFile() {
        String userDir = System.getProperty("user.home");
        return Paths.get(userDir, "occurences.dat").toFile();
    }

    /*
        Wykorzystywana jest wewnętrzna klasa statyczna do poprawnego
        utworzenia pojedynczej instancji klasy EventManager (wzorzec Singleton).
     */
    private static class InstanceWrapper {

        private static final EventManager INSTANCE = new EventManager();
    }

    public static void main(String[] args) {
        EventManager instance = EventManager.getInstance();
        System.out.println("EventManager.main");

        System.out.println("EventManager.main Wyzerowanie danych w pliku");
        instance.store();

        System.out.println("EventManager.main Generowanie zdarzeń");
        instance.notify(EventType.APPLICATION_STARTED);
        IntStream.range(1, 1 + new Random().nextInt(10)).forEach(value ->
                instance.notify(EventType.REQUEST_STARTED));
        System.out.println("EventManager.main Wystąpienia");
        instance.getOccurences()
                .forEach(((eventType, occurences) ->
                        System.out.println("EventManager.main " + eventType + ": " + occurences)));

        System.out.println("EventManager.main");
        System.out.println("EventManager.main Zapis do pliku...");
        instance.store();

        System.out.println("EventManager.main Generowanie zdarzeń");
        IntStream.range(1, 1 + new Random().nextInt(10)).forEach(value ->
                instance.notify(EventType.REQUEST_STARTED));
        System.out.println("EventManager.main Wystąpienia");
        instance.getOccurences()
                .forEach(((eventType, occurences) ->
                        System.out.println("EventManager.main " + eventType + ": " + occurences)));

        System.out.println("EventManager.main");
        System.out.println("EventManager.main Odczyt z pliku...");
        instance.load();
        System.out.println("EventManager.main Wystąpienia: ");
        instance.getOccurences()
                .forEach(((eventType, occurences) ->
                        System.out.println("EventManager.main " + eventType + ": " + occurences)));
    }
}
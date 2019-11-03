package pl.hit.servlets.listeners;

import pl.hit.servlets.generic.EventManager;
import pl.hit.servlets.generic.EventType;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestEventsHandler implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        EventManager.getInstance().notify(EventType.REQUEST_STARTED);
    }
}

package pl.hit.servlets.listeners;

import pl.hit.servlets.generic.EventManager;
import pl.hit.servlets.generic.EventType;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextEventsHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("eventManager", EventManager.getInstance());

        EventManager manager = (EventManager) servletContext.getAttribute("eventManager");
        manager.notify(EventType.REQUEST_STARTED);

    }
}

package pl.hit.servlets.generic;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/")

public class WelcomeServlet implements Servlet {

    @Override
    public void init(ServletConfig config) {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter writer = res.getWriter();
        writer.println("Hello, servlets world!");

        writer.println("Informacje o kliencie");
        writer.println("Host: " + req.getLocalName());
        writer.println("Port: " + req.getLocalPort());
        writer.println("Adres: " + req.getLocalAddr());
        writer.println();

        writer.println("Informacje o serverze");
        writer.println("Serwer: " + req.getServerName());
        writer.println("Host: " + req.getLocalName());
        writer.println("Port: " + req.getLocalPort());
        writer.println("Adres: " + req.getLocalAddr());
        writer.println();

        writer.println("Informacje o żądaniu");
        writer.println("Protokół: " + req.getProtocol());
        writer.println("Typ: " + req.getContentType());
        writer.println("Kodowanie: " + req.getCharacterEncoding());
        writer.println();

        writer.println("Parametry");
        req.getParameterMap().forEach((param, values) -> writer.println("\t" + param + "=" + Arrays.toString(values)));

        writer.println("Odpowiedź");
        writer.println("Kodowanie: " + req.getCharacterEncoding());
        writer.println("Typ: " + req.getContentType());


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
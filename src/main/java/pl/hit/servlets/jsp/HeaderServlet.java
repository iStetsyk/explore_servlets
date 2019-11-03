package pl.hit.servlets.jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class HeaderServlet extends HttpServlet {

    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<header><h1>Technologia JSP</h1>");
        out.println("<p>Mamy godzinę: " + LocalTime.now() + "</p>");
        out.println("<p>Strona została odwiedzona " + counter++ + " razy</p></header>");
    }
}

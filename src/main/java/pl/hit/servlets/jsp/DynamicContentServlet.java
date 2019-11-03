package pl.hit.servlets.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;

@WebServlet(urlPatterns = "/jsp/dynamic-content")

public class DynamicContentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("\t<title>Treść dynamiczna</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("<h1>\t\tTreść dynamiczna</h1>");
        out.println("<div>");

        Integer number = -10 + new Random().nextInt(21);
        out.println("<p>Wylosowana liczba: " + number + "</p>");

        if(number % 2 == 0) {
            out.println("Liczba jest parzysta");
        } else {
            out.println("Liczba nie jest parzysta");
        }

        out.println("</div>");
        out.println("<div>");
        out.println("<p>Teraz jest: " + LocalDateTime.now() + "</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }
}

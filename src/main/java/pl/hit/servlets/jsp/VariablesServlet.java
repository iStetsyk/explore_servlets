package pl.hit.servlets.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(urlPatterns = "/jsp/variables")
public class VariablesServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html;charset=UTF-8");

        out.println("<html>");
        out.println("<head><title>Obliczenia wyrażeń</title></head>");

        out.println("<body>");

        int a = -100 + new Random().nextInt(201);
        int b = -100 + new Random().nextInt(201);
        int c = -100 + new Random().nextInt(201);
        out.println("<h1>Obliczania matematyczne</h1>");
        out.println("<div><h2>Zmienne</h2>");
        out.println("<p> a = " + a + "</p>");
        out.println("<p> b = " + b + "</p>");
        out.println("<p> c = " + c + "</p>");
        out.println("</div>");

        out.println("<div><h2>Oblicznia</h2>");
        out.println("<p> a + b + c = " + (a + b + c) + "</p>");
        out.println("<p> a - b - c = " + (a - b - c) + "</p>");
        out.println("<p> a * b * c = " + (a * b * c) + "</p>");
        out.println("<p> a / b / c = " + (((double) a) / b / c) + "</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}

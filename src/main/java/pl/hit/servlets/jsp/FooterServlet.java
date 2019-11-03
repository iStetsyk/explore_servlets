package pl.hit.servlets.jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class FooterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<footer><div>");
        out.println("<span> Autor Igor</span>&nbsp;<span>Rok: " + LocalDate.now().getYear() + "</span>");
        out.println("</div></footer>");
    }
}

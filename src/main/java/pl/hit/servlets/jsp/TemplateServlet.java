package pl.hit.servlets.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/jsp/template-servlet")
public class TemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Szablon</title></head>");
        out.println("</body>");

        req.getServletContext().getNamedDispatcher("JspHeaderServlet").include(req, resp);

        out.println("<h2>Wiersz do technologii JSP</h2>");
        out.println("<p>Na górze róże</p>");
        out.println("<p>Na dole fiołki</p>");
        out.println("<p>A my się kochamy</p>");
        out.println("<p>Jak dwa aniołki</p>");

        req.getServletContext().getNamedDispatcher("JspFooterServlet").include(req, resp);

        out.println("</body></html>");
    }
}

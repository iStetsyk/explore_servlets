package pl.hit.servlets.http;

import pl.hit.servlets.generic.EventManager;
import pl.hit.servlets.generic.EventType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/diagnostics", loadOnStartup = 1)
public class DiagnosticServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        EventManager eventManager = (EventManager) this.getServletContext().getAttribute("eventManager");

        System.out.println("DiagnosticServlet.init Diagnostyka zdarzeń");
        eventManager.getOccurences().forEach(((eventType, count) -> System.out.println("DiagnosticServlet.init \t" + eventType + ": " + count)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        EventManager eventManager = (EventManager) req.getServletContext().getAttribute("eventManager");

        String format = req.getParameter("format");
        if (format == null) {
            writePlainDiagnostics(resp.getWriter(), eventManager.getOccurences());
        } else {
            switch (format) {
                case "plain":
                    resp.setContentType("text/plain");
                    writePlainDiagnostics(resp.getWriter(), eventManager.getOccurences());
                    break;
                case "html":
                    resp.setContentType("text/html");
                    writeHtmlDiagnostics(resp.getWriter(), eventManager.getOccurences());
                    break;
                case "xml":
                    resp.setContentType("application/xml");
                    writeXmlDiagnostics(resp.getWriter(), eventManager.getOccurences());
                    break;
                case "json":
                    resp.setContentType("application/json");
                    writeJsonDiagnostics(resp.getWriter(), eventManager.getOccurences());
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Podano zły format: " + format
                                    + ". Obsługuje wartości: plain, html, xml, json");
            }
        }
    }


    private void writePlainDiagnostics(PrintWriter writer, Map<EventType, Integer> occurences) {
        writer.println("Diagnostics: ");
        occurences.entrySet().forEach(entry -> writer.println(entry.getKey() + " = " + entry.getValue()));
    }

    private void writeHtmlDiagnostics(PrintWriter writer, Map<EventType, Integer> occurences) {
        StringBuilder html = new StringBuilder();
        html.append("<html>")
                .append("<head>")
                .append("<title>").append("Diagnostics").append("</title>")
                .append("<head>")
                .append("<body>")
                .append("<h1>").append("Diagnostics").append("</h1>")
                .append("<table>")
                .append("<tr>")
                .append("<th>").append("Zdarzenie").append("</th>")
                .append("<th>").append("Wystąpienia").append("</th>")
                .append("</tr>");

        occurences.entrySet().forEach(entry -> {
            html.append("<tr>")
                    .append("<td>").append(entry.getKey()).append("</td>")
                    .append("<td>").append(entry.getValue()).append("</td>")
                    .append("</tr>");
        });

        html
                .append("</html>")
                .append("</body>")
                .append("</html>");
        writer.println(html.toString());
    }

    private void writeXmlDiagnostics(PrintWriter writer, Map<EventType, Integer> occurences) {
        StringBuilder xml = new StringBuilder();
        xml.append("<diagnostics>");

        occurences.entrySet().forEach(entry -> {
            xml.append("<event>")
                    .append("<type>").append(entry.getKey()).append("</type>")
                    .append("<count>").append(entry.getValue()).append("</count>")
                    .append("</event");
        });

        xml.append("/diagnostics");
        writer.println(xml.toString());
    }

    private void writeJsonDiagnostics(PrintWriter writer, Map<EventType, Integer> occurences) {
        StringBuilder json = new StringBuilder();
        json.append("{")
                .append("\"diagnostics\" :")
                .append("[");

        occurences.entrySet().forEach(entry -> {
            json.append("{")
                    .append("\"name\" : \"").append(entry.getKey()).append("\",")
                    .append("\"count\" : \"").append(entry.getValue()).append("},");
        });
        json.deleteCharAt(json.length() - 1);

        json
                .append("]")
                .append("}");
        writer.println(json.toString());
    }
}
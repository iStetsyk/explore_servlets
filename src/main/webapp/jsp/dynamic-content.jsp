<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>

<html>
<head>
    <title>Treść dynamiczna</title>
</head>
<body>
<%  Integer number = -10 + new Random().nextInt(21); %>
    <h1>Treść dynamiczna</h1>
<div>
<%
    out.println("<p>Wylosowana liczba: " + number + "</p>");

    if(number % 2 == 0) {
        out.println("Liczba jest parzysta");
    } else {
        out.println("Liczba nie jest parzysta");
    }
%>
</div>
<div>
    <%
        out.println("<p>Teraz jest: " + LocalDateTime.now() + "</p>");
    %>
</div>
<div>
    <%
       out.println("<p>Mamy rok:" + LocalDate.now().getYear() + "</p>";
    %>
</div>
</body>
</html>

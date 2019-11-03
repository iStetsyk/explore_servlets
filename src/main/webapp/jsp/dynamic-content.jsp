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
<p>Wylosowana liczba: <%= number %> </p>
<%
    if(number % 2 == 0) { %>
    <p>Liczba jest parzysta</p>
    <% } else { %>
        <p>Liczba nie jest parzysta</p>
 <% } %>
</div>
<div>
  <p>Teraz jest: <%= LocalDateTime.now() %></p>
</div>
<div>
    <p>Mamy rok: <%= LocalDate.now().getYear() %></p>
</div>
</body>
</html>

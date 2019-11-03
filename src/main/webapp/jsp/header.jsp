<%@page import="java.time.LocalTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private int counter = 0; %>

<header>
    <h1>Technologia JSP</h1>
    <p>Mamy godzinę <%= LocalTime.now() %></p>
    <p>Strona została odwiedzona <%= counter++ %> razy</p>
</header>

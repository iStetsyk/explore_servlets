<%@page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Obliczenia wyrażeń</title>
</head>
<body>
<%
int a = -100 + new Random().nextInt(201);
int b = -100 + new Random().nextInt(201);
int c = -100 + new Random().nextInt(201);
%>
<h1>Obliczenia matematyczne</h1>
<div>
    <h2>Zmienne</h2>
    <p>a = <%= a%></p>
    <p>b = <%= b%></p>
    <p>c = <%= c%></p>
</div>
<div>
    <h2>Oblicznia</h2>
    <p>a + b + c = <%= a + b + c%></p>
    <p>a - b - c = <%= a - b - c%></p>
    <p>a * b * c = <%= a * b * c%></p>
    <p>a / b / c = <%= ((double) a) / b / c%></p>
</div>
</body>
</html>

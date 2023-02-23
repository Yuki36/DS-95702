<%--
 @author: Jiahui Zhu
 @email address: jiahuiz2@andrew.cmu.edu
 @Project number: Project1 Task3
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%= request.getAttribute("doctype") %>

<!DOCTYPE html>
<html>
<head>
    <title>Clicker</title>
</head>
<body>
    <h1><%= "Distributed Systems Class Clicker" %></h1>
    <% if (request.getParameter("choice") != null) { %>
        <p>Your "<%= request.getParameter("choice") %>" response has been registered</p>
    <%} %>
    <p>Submit your answer to the current question:</p>
    <form action="submit" method="POST">
        <input type="radio" id="a" name="choice" value="A">
        <label for="a">A</label><br>
        <input type="radio" id="b" name="choice" value="B">
        <label for="b">B</label><br>
        <input type="radio" id="c" name="choice" value="C">
        <label for="c">C</label><br>
        <input type="radio" id="d" name="choice" value="D">
        <label for="d">D</label><br>
        <input type="submit" value="Submit">
    </form>

<br/>
</body>
</html>
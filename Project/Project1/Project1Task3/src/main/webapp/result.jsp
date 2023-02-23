<%--
 @author: Jiahui Zhu
 @email address: jiahuiz2@andrew.cmu.edu
 @Project number: Project1 Task3
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%= request.getAttribute("doctype") %>
<html>
<head>
    <title>Get results</title>
</head>
<body>
    <h1>Distributed Systems Class Clicker</h1>
    <% if ((request.getAttribute("AChoice").equals(0)) && (request.getAttribute("BChoice").equals(0))
            && (request.getAttribute("CChoice").equals(0)) && (request.getAttribute("DChoice").equals(0))) {%>
        <p>There are currently no results</p>
    <%} else {%>
        <p>The results from the survey are as follows:</p>
        <% if (!request.getAttribute("AChoice").equals(0)) {%>
            <p>A : <%=request.getAttribute("AChoice")%></p>
        <%} %>
        <% if (!request.getAttribute("BChoice").equals(0)) {%>
            <p>B : <%=request.getAttribute("BChoice")%></p>
        <%} %>
        <% if (!request.getAttribute("CChoice").equals(0)) {%>
            <p>C : <%=request.getAttribute("CChoice")%></p>
        <%} %>
        <% if (!request.getAttribute("DChoice").equals(0)) {%>
            <p>D : <%=request.getAttribute("DChoice")%></p>
        <%} %>
    <%}%>
</body>
</html>

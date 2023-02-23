<%--
  User: jiahuizhu
  Email address: jiahuiz2@andrew.cmu.edu
  Project number: Project1 Task2
  Date: 2/10/23
  Time: 4:52 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country Found</title>
</head>
<body>
    <h1>Country: <%= request.getParameter("country") %></h1><br>
    <h2>Nickname: <%= request.getAttribute("nickName") %></h2>
    <p> www.topendsports.com/sport/soccer/team-nicknames-women.htm</p><br>
    <h2>Capital City: <%= request.getAttribute("capitalCity") %></h2>
    <p> www.restcountries.com</p><br>
    <h2>Top Scorer in 2019: <%= request.getAttribute("topScorer") %></h2>
    <p> www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring</p><br>
    <h2>Flag: </h2><br>
    <% if (request.getAttribute("flag") != null) { %>
        <img src="<%=request.getAttribute("flag")%>" width="200" height="200" >
    <% } %>
    <p> www.cia.gov/the-world-factbook/countries/</p><br>
    <h2>Flag Emoji: </h2><br>
    <% if (request.getAttribute("flagEmoji") != null) { %>
    <img src="<%=request.getAttribute("flagEmoji")%>" width="200" height="200" >
    <% } %>
    <p> www.cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/your-country-here.svg</p><br>

    <form action="WorldCupServlet" method="GET">
            <input type="submit" value="Continue"/>
    </form>
</body>
</html>

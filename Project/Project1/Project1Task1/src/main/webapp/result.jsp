<%--
  User: jiahuizhu
  Email Address: jiahuiz2Wandrew.cmu.edu
  Project Number: Project1 Task1
  Date: 2/8/23
  Time: 7:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compute New Word Hash</title>
</head>
<body>
<h1><%= "Compute Hashes in two different way!" %>
</h1>
<%--Add compute action result --%>
<h1 class="title">Here is the Base64 <%= request.getAttribute("comp_function")%> Hash Code of <%= request.getAttribute("textContent")%> :</h1>
<h2 class="title"> <%= request.getAttribute("res_base64")%> </h2><br>

<h1 class="title">Here is the Hexadecimal <%= request.getAttribute("comp_function")%> Hash Code of <%= request.getAttribute("textContent")%> :</h1>
<h2 class="title"> <%= request.getAttribute("res_hex")%> </h2><br>

<h1 class="title">Compute Hashes</h1>

<%--Show the compute function again for user after showing the result --%>
<form action="ComputeHashes" method="GET">
    <label for="textContent"> Text:</label><br>
    <input type="text" id="textContent" name="textContent"><br>
    <input type="radio" id="md5" name="comp_function" value="MD5">
    <label for="md5">MD5</label><br>
    <input type="radio" id="SHA256" name="comp_function" value="SHA-256">
    <label for="SHA256">SHA-256</label><br>
    <input type="submit" value="Compute Hashes">
</form>
</body>
</html>

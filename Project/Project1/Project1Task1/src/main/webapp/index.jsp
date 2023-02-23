<%--
  author: jiahuizhu
  Email Address: jiahuiz2Wandrew.cmu.edu
  Project Number: Project1 Task1
  Date: 2/9/23
  Time: 3:24 PM
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>

<h1><%= "Compute Hashes in two different way!" %>
</h1>
<%--Add compute action function --%>
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
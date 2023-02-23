<%--
  User: jiahuizhu
  Email address: jiahuiz2@andrew.cmu.edu
  Project number: Project1 Task2
  Date: 2/10/23
  Time: 4:52 AM
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Women's World Cup 2023</title>
</head>
<body>
<h1><%= "Women's World Cup 2023" %></h1>
<p><%= "Created by Jiahui Zhu" %></p>
<h2><%= "Participating Countries" %></h2>
<br/>

<form action="WorldCupServlet" method="GET">
    <label for="country"> Choose a country:</label><br>
    <select name="country" id="country">
        <option value="Argentina">Argentina</option>
        <option value="Australia">Australia</option>
        <option value="Brazil">Brazil</option>
        <option value="Canada">Canada</option>
        <option value="China">China</option>
        <option value="Colombia">Colombia</option>
        <option value="Costa Rica">Costa Rica</option>
        <option value="Denmark">Denmark</option>
        <option value="England">England</option>
        <option value="France">France</option>
        <option value="Germany">Germany</option>
        <option value="Ireland">Ireland</option>
        <option value="Italy">Italy</option>
        <option value="Jamaica">Jamaica</option>
        <option value="Japan">Japan</option>
        <option value="Morocco">Morocco</option>
        <option value="Netherlands">Netherlands</option>
        <option value="NewZealand">New Zealand</option>
        <option value="Nigeria">Nigeria</option>
        <option value="Norway">Norway</option>
        <option value="Philippines">Philippines</option>
        <option value="South Africa">South Africa</option>
        <option value="South Korea">South Korea</option>
        <option value="Spain">Spain</option>
        <option value="Sweden">Sweden</option>
        <option value="Switzerland">Switzerland</option>
        <option value="United States">United States</option>
        <option value="Vietnam">Vietnam</option>
        <option value="Zambia">Zambia</option>
    </select><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
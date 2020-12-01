
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Weather Data</title>

</head>
<body>

<h4>City Name:<%=request.getParameter("name")%>
<h4>Humidity(%):<%=request.getParameter("humidity")%></h4>
<h4>Temperature (F):<%=request.getParameter("temp")%></h4>
<h4>Temperature Feels Like (F):<%=request.getParameter("tempFeel")%></h4>
<h4>Temperature High (F):<%=request.getParameter("tempMin")%></h4>
    <h4>Temperature Low (F):<%=request.getParameter("tempMax")%></h4>

<h4>Select a City</h4>
<form method="get" action="/get/">
    <select name="id">
    <option value="4684904">Dallas</option>
    <option value="4699066">Houston</option>
        <option value="4671654">Austin</option>
    </select>
    <input type="submit" name="Select" value="Call/Save">


</form>

<form method="get" action="/load">
    <input type="submit" value="Load Table in Database">
</form>


<table>

    <tr>
        <th>City</th>
        <th>Humidity(%)</th>
        <th>Temperature(F)</th>
        <th>Temp Feels Like(F)</th>
        <th>TempMax(F)</th>
        <th>TempMin(F)</th>
    </tr>
    <c:forEach var="weather" items = "${weatherlist}">
        <tr>
            <td>${weather.getName()}</td>
            <td>${weather.getHumidity()}</td>
            <td>${weather.getTemp()}</td>
            <td>${weather.getTempFeel()}</td>
            <td>${weather.getTempMax()}</td>
            <td>${weather.getTempMin()}</td>
            <td>
                <a href="/delete/${weather.getId()}">Delete</a>
            </td>

        </tr>
    </c:forEach>




</table>



</body>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Market Place</h2></caption>
            <<tr>
                <th>saleID</th>
                <th>Price</th>
                <th>End Date</th>
               
            </tr>
            <c:forEach var="MarktetPlace" items="${listMarketPlace}">
                <tr style="text-align:center">
                    <td><c:out value="${MarketPlace.saleID}" /></td>
                    <td><c:out value="${MarketPlace.endDate}" /></td>
                    <td><c:out value="${MarketPlace.price}" /></td>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
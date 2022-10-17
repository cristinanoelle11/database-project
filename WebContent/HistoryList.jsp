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
            <caption><h2>List of History</h2></caption>
            <<tr>
                <th>HistoryID</th>
                <th>UserID</th>
                <th>NFT ID</th>
                <th>Details</th>
                <th>Action</th>
                <th>Date</th>
               
            </tr>
            <c:forEach var="history" items="${listHistory}">
                <tr style="text-align:center">
                	<td><c:out value="${history.historyID}" /></td>
                	<td><c:out value="${history.userID}" /></td>
                    <td><c:out value="${history.nftID}" /></td>
                    <td><c:out value="${history.details}" /></td>
                    <td><c:out value="${history.action}" /></td>
                    <td><c:out value= "${history.date}" /></td>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
	
	<li class =stats>
		<form action="stats" method="post">
			<input class ="submitHome" type="submit" value="statistics"/>
		</form>
	</li>
	
	<br><br>
<h1>All the tables for the website</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
            	<th>UserID</th>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Age</th>
                <th>wallet($)</th>
                
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
               		<td><c:out value="${users.userID}" /></td>
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.age}" /></td>
                    <td><c:out value="${users.wallet}"/></td>
            </c:forEach>
        </table>
              <table border="1" cellpadding="6">
            <caption><h2>List of NFTS</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Owner</th>
                
            </tr>
            <c:forEach var="nft" items="${listNFT}">
                <tr style="text-align:center">
                    <td><c:out value="${nft.nftID}" /></td>
                    <td><c:out value="${nft.name}" /></td>
                    <td><c:out value="${nft.description}" /></td>
                    <td><c:out value= "${nft.image}" /></td>
                    <td><c:out value="${nft.owner}" /></td>
            </c:forEach>
        </table>
         <table border="1" cellpadding="5">
            <caption><h2>List of History</h2></caption>
            <tr>
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
                    <td><c:out value="${history.date}" /></td>
            </c:forEach>
        </table>
         <table border="1" cellpadding="5">
            <caption><h2>Market Place</h2></caption>
            <tr>
                <th>saleID</th>
                <th>End Date</th>
                <th>Price</th>
                <th>nftID</th>
               
            </tr>
            <c:forEach var="marketPlace" items="${listMarketPlace}">
                <tr style="text-align:center">
                    <td><c:out value="${marketPlace.saleID}" /></td>
                    <td><c:out value="${marketPlace.endDate}" /></td>
                    <td><c:out value="${marketPlace.price}" /></td>
                    <td><c:out value="${marketPlace.nftID}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>
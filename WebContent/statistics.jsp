<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statistics</title>
</head>
<body>
<h2> ${errorMessage } </h2>
	<div class = "flexcontainer">
		<div class = "flexbox">
			<p>6. Click to list the users who purchased some NFTs and then have never sold them afterwards. </p>
			<form action = "diamondHands">
					<input type = "submit" value = "diamondHands"/>
			</form>
			<c:if test="${fn:length(diamondHands) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			            </tr>
			            <c:forEach var="users" items="${diamondHands}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			                    <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>7. Click to list the users who have purchased some NFTs but have sold all of them (not transferred).</p>
			<form action = "paperHands">
					<input type = "submit" value = "paperHands"/>
			</form>
			<c:if test="${fn:length(paperHands) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			            </tr>
			            <c:forEach var="users" items="${paperHands}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			                    <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>8. Click to list the users who have purchased at least 3 NFTs.</p>
			<form action = "goodBuyers">
					<input type = "submit" value = "goodBuyers"/>
			</form>
			<c:if test="${fn:length(goodBuyers) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			            </tr>
			            <c:forEach var="users" items="${goodBuyers}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			                    <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
			
		<div class = "flexbox">
			<p>9. List those users who have never performed NFT activities (minting, buying, selling, and transferring).</p>
			<form action = "inactiveUsers">
					<input type = "submit" value = "inactiveUsers"/>
			</form>
			<c:if test="${fn:length(inactiveUsers) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			            </tr>
			            <c:forEach var="users" items="${inactiveUsers}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			                    <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>10. Enter a users email to list the total numbers of buys, sells, transfers, and the number of tNFTs that the user owns now. </p>
			<form action = "userStats"method="post">
			Enter users email: <input type="text" id = "search" name="name">
			<input type="submit" value="Submit"/>
			</form>
			<c:if test="${fn:length(userStats) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2><c:out value="${currentU.firstName}" /> : <c:out value="${currentU.email}" /></h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Action</th>  
			              <th>Count</th> 
			            </tr>
			            <c:forEach var="users" items="${userStats}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			                    <td><c:out value="${users.action}" /></td>
			                  <td><c:out value="${users.count}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
	</div>
</body>
</html>
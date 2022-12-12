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
	<li class ="transfer">
		<form action="root" method="post">
			<input class ="submitHome" type="submit" value="BACK"/>
		</form>
	</li>
<h2> ${errorMessage } </h2>
	<div class = "flexcontainer">
	
	<div class = "flexbox">
			<p>1.  Click to list the users who created the most number of NFTs. </p>
			<form action = "bigCreators">
					<input type = "submit" value = "bigCreators"/>
			</form>
			<c:if test="${fn:length(bigCreators) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			                
			            </tr>
			            <c:forEach var="users" items="${bigCreators}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			               		 <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>2.  Click to list the users who sold the most number of NFTs. </p>
			<form action = "bigSellers">
					<input type = "submit" value = "bigSellers"/>
			</form>
			<c:if test="${fn:length(bigSellers) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			                
			            </tr>
			            <c:forEach var="users" items="${bigSellers}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			               		 <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>3.  Click to list the users who bought the most number of NFTs. </p>
			<form action = "bigBuyers">
					<input type = "submit" value = "bigBuyers"/>
			</form>
			<c:if test="${fn:length(bigBuyers) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <caption><h2>List of Users</h2></caption>
			            <tr>
			            	<th>UserID</th>
			                <th>Email</th>  
			                
			            </tr>
			            <c:forEach var="users" items="${bigBuyers}">
			                <tr style="text-align:center">
			               		<td><c:out value="${users.userID}" /></td>
			               		 <td><c:out value="${users.email}" /></td>
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class = "flexbox">
			<p>4.  Click to list the hottest NFTs. </p>
			<form action = "hottestNfts">
					<input type = "submit" value = "hottestNfts"/>
			</form>
			<c:if test="${fn:length(hottestNfts) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <h2>List of Hottest Nfts</h2>
			            <tr>
			            	<th>nftID</th>  
			            	<th>name</th>
			                
			            </tr>
			            <c:forEach var="Nfts" items="${hottestNfts}">
			                <tr style="text-align:center">
			               		<td><c:out value="${Nfts.nftID}" /></td>
			               		<td><c:out value="${Nfts.name}" /></td>
			               		
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
		
		<div class="flexbox">
			<p>5.  Submit to find common NFTs between users. </p>
			<form action="commonNfts" method="post">
	    		Select first user:&nbsp;
	   			 <select name="user1">
			        <c:forEach items="${listUser}" var="category">
			            <option value="${category.userID}"><c:out value="${category.userID}" /></option>
			        </c:forEach>
	   			</select>
	   			Select second user:&nbsp;
	   			  <select name="user2">
			        <c:forEach items="${listUser}" var="category">
			            <option value="${category.userID}"><c:out value="${category.userID}" /></option>
			        </c:forEach>
	   			</select>
	    		<br/><br/>
	    		<input type="submit" value="Submit" />
			</form>
			<c:if test="${fn:length(commonNFTS) > 0}">
				<div align="center">
			        <table border="1" cellpadding="6">
			            <h2>NFTs that both <c:out value="${user1.firstName}" /> (userID: <c:out value="${user1.userID}" />) AND <c:out value="${user2.firstName}" /> (userID: <c:out value="${user2.userID}" />) have owned</h2>
			            <tr>
			            	<th>nftID</th>  
			            	<th>name</th>
			            </tr>
			            <c:forEach var="Nfts" items="${commonNFTS}">
			                <tr style="text-align:center">
			               		<td><c:out value="${Nfts.nftID}" /></td>
			               		<td><c:out value="${Nfts.name}" /></td>
			               		
			            </c:forEach>
			        </table>
				</div>
			</c:if>
		</div>
			
		
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
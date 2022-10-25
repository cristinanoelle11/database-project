<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

	<body>
	
<center><h1>Welcome <c:out value="${currentU.firstName}" />! You have been successfully logged in</h1> </center>
	 <p>your current balance is <c:out value="${currentU.wallet}" /> in (EUH)</p>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <h3>Your NFTS:</h3>
		 </center>
		 <c:forEach var="nfts" items="${usersNFTS}">
		<h1><c:out value="${nfts.name}" /></h1>
    <div align="center">
            <img src = "<c:out value= "${nfts.image}" />"width="300" height="400">
            <p><c:out value="${nfts.description}" /></p>
            </div>
            </c:forEach>
		 <form action="search" method="post">
			Search NFTs By Name: <input type="text" id = "search" name="name">
			<input type="submit" value="Search"/>
		</form>
		
	</body>
</html>
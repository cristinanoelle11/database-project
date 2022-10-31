<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${nft.name}" /></title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:forEach var="nft" items="${certainNFT}">
<h1><c:out value="${nft.name}" /></h1>
    <div align="center">
            <img src = "<c:out value= "${nft.image}" />"width="300" height="400">
            <p><c:out value="${nft.description}" /></p>
             
            
            	<p>Price = <c:out value="${nft.price}" />(eth)</p>
            <c:if test = "${nft.owner == currentUser.userID}">
            
            <form action = "cancel" method="post" >
            	<input type = hidden name = "name" value ="${nft.name}" />
            	<input type = hidden name = "nftID" value = "${nft.nftID}" />
				<input type="submit" value="CANCEL"/>
			</form>
            </c:if>
            <c:if test = "${nft.owner != currentUser.userID}">
            <form action = "buy" method="post" >
            	<input type = hidden name = "name" value ="${nft.name}" />
            	<input type = hidden name = "nftID" value = "${nft.nftID}" />
				<input type="submit" value="BUY ME"/>
			</form>
            </c:if>
            
			</c:forEach>
             
           
       
	</div>
</body>
</html>
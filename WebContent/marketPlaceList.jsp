<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${nft.name}" /></title>
<style>
	.flexcontainer {
		margin:10px;
	  	display: flex;
        flex-wrap: wrap;
        margin-left:auto;
		margin-right:auto;
	}
	.listing{
		margin-left:auto;
		margin-right:auto;
		
	}
	.nftImage{
		width:350px;
		height:350px;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
	<center><h1>MarketPlace</h1></center>
	<div class = "flexcontainer">
	<c:forEach var="market" items="${listMarketPlace}">
	
		<div class = "listing">
			<h3><c:out value="${market.name}" /></h3>
			<p>Price = <c:out value="${market.price}" /></p>
			<img class ="nftImage" src = "<c:out value= "${market.image}" />">
		</div>
		</c:forEach>
	</div>
	

            	
           
         
			
             
           
       
	
</body>
</html>
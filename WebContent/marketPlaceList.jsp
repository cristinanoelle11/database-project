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
		width:350px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	p{
	display:inline;
	}
	h3{
	display:inline;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
	<center><h1>MarketPlace</h1></center>
	<c:forEach var="nft" items="${listNFT}">
	<c:forEach var="market" items="${listMarketPlace}">
	<c:if test="${nft.nftID == market.nftID}">
	<div class = "flexcontainer">
	<div class = "listing">
		<h3><c:out value="${nft.name}" /></h3>
		<p>Price = <c:out value="${market.price}" /></p>
	</div>
	</div>
	</c:if>
		</c:forEach>
	</c:forEach>
            	
           
         
			
             
           
       
	
</body>
</html>
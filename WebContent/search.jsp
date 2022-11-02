<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
<style>
	.flexcontainer2{
		margin:6px;
	  	display: flex;
        flex-wrap: wrap;
        margin-left:auto;
		margin-right:auto;
		width:850px;
	}
	.nftC{
		margin-left:auto;
		margin-right:auto;
	}
		.flexcontainer {
		margin:10px;
	  	display: flex;
	  	
        flex-wrap: wrap;
        margin-left:auto;
		margin-right:auto;
	}
	.searchBlock{
		display:block;	
	}
	.names{
		width:200px;
		height:80px;
		margin-left:auto;
		margin-right:auto;
	}
	.nftNames{
		display:inline;
		border: 1px solid black;
		padding:10px;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
	<p>${error} </p>
	<div class = "searchBlock">
		 <form action="search" method="post">
			Search NFTs By Name: <input type="text" id = "search" name="name">
			<input type="submit" value="Search"/>
		</form>
	</div>
		
	<center><h4>NFTs on the MarketPlace:</h4></center>
		<div class = "flexcontainer2">
			<c:forEach var="nft" items="${listNFT}">
			<c:forEach var="market" items="${listMarketPlace}">
			<c:if test="${nft.nftID == market.nftID}">
					<div class = "names">
						<p class = "nftNames"><c:out value="${nft.name}" /></p>
					</div>
			</c:if>
			</c:forEach>
			</c:forEach>
		</div>
		
</body>
</html>
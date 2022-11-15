<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>searchNFT</title>
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

<div class = "searchBlock">
		 <form action="searchNFT2" method="post">
			Search for NFTs by NFT name: <input type="text" id = "search" name="nftName">
			<input type="submit" value="Search"/>
		</form>
</div>	
	<c:forEach var="nftNames" items="${nftNames}">
		<form action="displayNFT" method="post">
			<input type="submit" name = "names" value="<c:out value="${nftNames.name}" />"/>
		</form>
	</c:forEach>
</body>
</html>
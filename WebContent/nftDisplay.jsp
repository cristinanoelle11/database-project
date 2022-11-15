<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NFT Display</title>
<style>
	.flexcontainer {
		margin:10px;
	  	display: flex;
        flex-wrap: wrap;
        margin-left:auto;
		margin-right:auto;
	}
	.nftImage{
		width:350px;
		height:350px;
	}
	.nftC{
		margin-left:auto;
		margin-right:auto;
	}
	.wallet{
		display: inline;
	}

</style>
</head>
<body>
	<jsp:include page="header.jsp" />

    	    <div class ="nftC">
	   			<h4 class ="nftName"><c:out value="${nft.name}" /> (nftID: <c:out value="${nft.nftID}" />)</h4>
	            <img class ="nftImage" src = "<c:out value= "${nft.image}" />">
	            <p class ="nftDescrip"><c:out value="${nft.description}" /></p>
	        </div>
</body>
</html>
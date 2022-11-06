<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SELL</title>
<style>
	.listBlock{
		display:block;
		width:550px;
		height:150px;
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
	.wallet{
		display: inline;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
<div class = "listBlock">
		 <form action="placeInMarket" method="post">
		  <p>${errorOne} </p>
		 <h4>List NFT onto MarketPlace</h4>
			NFT Name: <input type="text"  name="name"><br>
			What Price (ETH) for NFT <input type="number" name="price" ><br>
			What date would you like it to not be listed anymore?<input type="text" name="date"><br>
			<input type="submit" value="List"/>
		</form>
		</div>   
		<h1>NFTS you have previously sold</h1>
		<div class = "flexcontainer">
		<c:forEach var="history" items="${listHistory}">
			<c:if test="${history.userID == currentUser.userID}">
				<c:forEach var="nft" items="${listNFT}">
					<c:if test="${history.nftID == nft.nftID && history.action == 'sold'}">
						<div class ="nftC">
								<h3><c:out value="${nft.name}" /></h3>
					            <img src = "<c:out value= "${nft.image}" />"width="350" height="350">
					            <p><c:out value="${nft.description}" /></p>
					    </div>
					</c:if>
				</c:forEach>  
			</c:if>
		</c:forEach>
		</div>
</body>
</html>
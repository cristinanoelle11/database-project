<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>SELL</title>
	<script>
		function changedata(){
		      var x = document.getElementById('displayMint');
		      x.style.display = 'block';
		}
	</script>
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
<h3>${errorDate}</h3>
<div class = "listBlock">
		 <form action="placeInMarket" method="post">		
		  <p>${errorOne} </p>
		  <p>${messageNotEqual}</p>
		 <h4>List NFT onto MarketPlace</h4>
			NFT Name: <input type="text"  name="name"><br>
			What Price (ETH) for NFT <input type="number" name="price" ><br>
			What date would you like it to not be listed anymore (MM/DD/YYYY)?<input type="text" name="date"><br>
			<input type="submit" value="List"/>
		</form>
		</div>
	<center><button class="button" onclick="changedata()">NFTS SOLD</button></center>
	<div  ID="displayMint" STYLE="display:none">   
		<c:if test="${fn:length(result) > 0}">
			<h1>NFT's you have previously listed:</h1>
		</c:if>
		<c:if test="${empty result }">
			<h1>You have not sold any NFTs</h1>
		</c:if>
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
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <!DOCTYPE html>
<html>
<head>
	<title>Mint NFT</title>
	<script>
		function changedata(){
		      var x = document.getElementById('displayMint');
		      x.style.display = 'block';
		}
	</script>
	<style>
		.flexcontainer {
			margin:10px;
		  	display: flex;
	        flex-wrap: wrap;
	        margin-left:auto;
			margin-right:auto;
		}
		.nftImage{
			width:150px;
			height:150px;
		}
		.nftC{
			margin-left:auto;
			margin-right:auto;
		}
	</style>
</head>
<body>
<jsp:include page="header.jsp" />
<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<h1>Create an NFT!</h1>
		<form action="mintNFT">
			<table border="1" cellpadding="5">
				<tr>
					<th>NFT Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="name" size="45"  value="fireball" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Image URL: </th>
					<td align="center" colspan="3">
						<input type="text" name="image" size="45" value="URL" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Description: </th>
					<td align="center" colspan="3">
						<input type="text" name="description" size="45" value="firey-ball" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Mint"/>
					</td>
				</tr>
				
			</table>
			
		</form>
	</div>
	
	<button class="button" onclick="changedata()">NFTS CREATED</button>
	<div  ID="displayMint" STYLE="display:none">
		<c:if test="${fn:length(result) > 0}">
			<h1>${messageOne}</h1>
		</c:if>
		<c:if test="${empty result }">
			<h1>You have not created any NFTs</h1>
		</c:if>
			<div class = "flexcontainer">
			<c:forEach var="history" items="${listHistory}">
				<c:if test="${history.userID == currentUser.userID}">
					<c:forEach var="nft" items="${listNFT}">
						<c:if test="${history.nftID == nft.nftID && history.action == 'mint'}">
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
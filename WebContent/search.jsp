<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	.usersbox{
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
	.flexcontainer1 {
		margin:10px;
	  	display: flex;
        flex-wrap: wrap;
        margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
	<p>${error} </p>
	<p>${poorError }</p>
	<div class = "searchBlock">
		 <form action="search" method="post">
			Search NFTs By Name: <input type="text" id = "search" name="name">
			<input type="submit" value="Search"/>
		</form>
	</div>	
		<c:if test="${empty nfts}">
		<h1>${errorOne}</h1>
	</c:if>
	<c:if test="${fn:length(nfts) > 0}">
		<h1>${messageOne}</h1>
	</c:if>
	<div class = "flexcontainer">
		<c:forEach var="nfts" items="${nfts}">
			<div class ="usersbox">
				<form action="displayNFT" method="post">
					<input type="submit" name = "name" value="<c:out value="${nfts.name}" />"/>
				</form>
			</div>
		</c:forEach>
	</div>
	<br><br>
	<c:if test="${fn:length(result) > 0}">
		<h1>${message}</h1>
	</c:if>
		<div class = "flexcontainer1">
		<c:forEach var="history" items="${listHistory}">
			<c:if test="${history.userID == currentUser.userID}">
				<c:forEach var="nft" items="${listNFT}">
					<c:if test="${history.nftID == nft.nftID && history.action == 'bought'}">
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>
	<body>

	<jsp:include page="header.jsp" />
	<center><h1>Welcome <c:out value="${currentU.firstName}" />! You have been successfully logged in</h1> </center>
	<h2 class ="wallet">Wallet: </h2><p class = "wallet"><c:out value="${currentU.wallet}" /> in (EUH)</p>
	<h3>Your NFTS:</h3>

		<div class = "flexcontainer"> 
			<c:forEach var="nfts" items="${usersNFTS}">
			<c:if test="${nfts.owner} == ${currentU.userID}">
			<p>${noNFTStr}</p>
			</c:if>
	    	<div class ="nftC">
	   			<h4 class ="nftName"><c:out value="${nfts.name}" /></h1>
	            <img class ="nftImage" src = "<c:out value= "${nfts.image}" />">
	            <p class ="nftDescrip"><c:out value="${nfts.description}" /></p>
	        </div>
	         </c:forEach>
	     </div>
	</body>
</html>
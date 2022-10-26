<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
	h1 {color:red;}
	p {color:blue;}
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
	.searchBlock{
		display:block;
		
		height:150px;
	}
	.listBlock{
		display:block;
		width:550px;
		height:350px;
	}
</style>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

	<body>
	
<center><h1>Welcome <c:out value="${currentU.firstName}" />! You have been successfully logged in</h1> </center>
	 <p>your current balance is <c:out value="${currentU.wallet}" /> in (EUH)</p>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <h3>Your NFTS:</h3>
		 </center>
		<div class = "flexcontainer"> 
			<c:forEach var="nfts" items="${usersNFTS}">
	    	<div class ="nftC">
	   			<h4 class ="nftName"><c:out value="${nfts.name}" /></h1>
	            <img class ="nftImage" src = "<c:out value= "${nfts.image}" />">
	            <p class ="nftDescrip"><c:out value="${nfts.description}" /></p>
	        </div>
	         </c:forEach>
	     </div>
         <div class = "searchBlock">
		 <form action="search" method="post">
			Search NFTs By Name: <input type="text" id = "search" name="name">
			<input type="submit" value="Search"/>
		</form>
		</div>
		<div class = "listBlock">
		 <form action="placeInMarket" method="post">
		 <h4>List NFT onto MarketPlace</h4>
			NFT Name: <input type="text"  name="name"><br>
			What Price (ETH) for NFT <input type="number" name="price" ><br>
			What date would you like it to not be listed anymore?<input type="text" name="date"><br>
			
			<input type="submit" value="List"/>
		</form>
		</div>
	</body>
</html>
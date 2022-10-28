<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.listBlock{
		display:block;
		width:550px;
		height:350px;
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
</body>
</html>
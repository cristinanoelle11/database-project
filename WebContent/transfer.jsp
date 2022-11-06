<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer</title>
</head>
<body>
<jsp:include page="header.jsp" />
<center><h1>Welcome <c:out value="${currentU.firstName}" />! Transfer NFT?</h1> </center>
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
		<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="transfer">
			<table border="1" cellpadding="5">
				<tr>
					<th>Transfer TO User Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45"  value="name@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Nft To Transfer: </th>
					<td align="center" colspan="3">
						<input type="text" name="name" size="45" value="Apple" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Transfer"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>

</body>
</html>
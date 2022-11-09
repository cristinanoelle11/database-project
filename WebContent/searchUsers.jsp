<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</style>
</head>
<body>
<jsp:include page="header.jsp" />

<div class = "searchBlock">
		 <form action="searchUsers" method="post">
			Search for Users by Email: <input type="text" id = "search" name="email">
			<input type="submit" value="Search"/>
		</form>
	</div>	
	<c:forEach var="users" items="${users}">
		<form action="displayUser" method="post">
			<input type="submit" name = "name" value="<c:out value="${users.email}" />"/>
		</form>
	</c:forEach>
</body>
</html>
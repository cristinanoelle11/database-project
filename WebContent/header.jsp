<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
.menuBar{
		padding-bottom:10px;
	}
	ul {
	  list-style-type: none;
	  padding: 0;
	  overflow: hidden;
	  background-color: rgb(157, 191, 158);
	}
	li {
	  float: right;
	}
	li form {
	  display: block;
	  text-color: white;
	  text-align: center;
	  padding: 14px 16px;
	  cursor: pointer;
	  text-decoration: none;
	}
	li a {
	  display: block;
	  color: white;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	}
	.submitHome{
		font-size:15px;
		color: white;
		list-style-type: none;
		margin: 0;
		cursor: pointer;
		padding: 0;
		overflow: hidden;
		background-color: rgb(157, 191, 158);
		text-decoration:none;
		border:none;
	}
</style>
</head>
<body>
<div class = "menuBar">
			<ul>
				<li> <a href="login.jsp"target ="_self" >LOGOUT</a></li>
				<li><a href="ListNFT.jsp"target ="_self" >SELL</a></li>
				<li class ="search">
				  	<form action="searchNFT" method="post">
						<input class ="submitHome" type="submit" value="SEARCH"/>
					</form>
				</li>
				<li class ="home">
				  	<form action="activity" method="post">
						<input class ="submitHome" type="submit" value="HOME"/>
					</form>
				</li>
			</ul>
	</div>
</body>
</html>
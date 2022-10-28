<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Mint NFT</title></head>
<body>
<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
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
			<a href="marketPlaceList.jsp" target="_self">Return to Market Place</a>
		</form>
	</div>
</body>
</html>
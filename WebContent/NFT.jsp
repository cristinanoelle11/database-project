<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>List all users</h1>
    <div align="center">
       </table>
              <table border="1" cellpadding="6">
            <caption><h2>List of NFTS</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Owner</th>
                
            </tr>
            <c:forEach var="nft" items="${certainNFT}">
                <tr style="text-align:center">
                    <td><c:out value="${nft.nftID}" /></td>
                    <td><c:out value="${nft.name}" /></td>
                    <td><c:out value="${nft.description}" /></td>
                    <td><c:out value= "${nft.image}" /></td>
                    <td><c:out value="${nft.owner}" /></td>
            </c:forEach>
        </table>
	</div>
</body>
</html>
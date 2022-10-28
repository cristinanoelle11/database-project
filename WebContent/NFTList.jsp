<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of NFTS</h2></caption>
            <<tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Owner</th>
               
            </tr>
            <c:forEach var="nfts" items="${listNFT}">
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
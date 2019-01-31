<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Articles</title>
</head>
<body>
<div><a href="addArticle">Add new article</a> </div>
<div><a href="wordCount.jsp">Count occurrences of a word in string</a> </div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Articles</h2></caption>
        <thead>
        <tr>
            <th>Id </th>
            <th>URL </th>
            <th>Title </th>
            <th>Text </th>
            <th>Category </th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${listArticle}">
        <tr>
            <td><c:out value="${article.article_id}"/></td>
            <td><c:out value="${article.url}"/></td>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.article_text}"/></td>
            <td><c:out value="${article.category_id}"/></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
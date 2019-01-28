<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Article</title>
</head>
<body>
<h1>Create Article</h1>
<h2>
    <a href="/wordCount.jsp">Count of words in article</a>
</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Articles</h2></caption>
        <tr>
            <th>Id: </th>
            <th>URL: </th>
            <th>Title: </th>
            <th>Text: </th>

        </tr>
        <c:forEach var="article" items="${listArticle}">
        <tr>
            <td><c:out value="${article.article_id}"/></td>
            <td><c:out value="${article.url}"/></td>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.article_text}"/></td>
        </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
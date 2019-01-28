<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
</head>
<body>
<div align="center">
<table border="1" cellpadding="5">
    <caption><h2>Counter result</h2></caption>
    <tr>
        <th>Word: </th>
        <th>Quantity: </th>

    </tr>
    <tr>
        <td><% request.getParameter("word"); %></td>
        <td><% request.getParameter("quantity"); %></td>
    </tr>
</table>
</div>

</body>
</html>

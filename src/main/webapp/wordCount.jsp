<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get word count</title>
</head>
<body>
<form action="wordCount" method="get">
    <table style="with: 50%">
        <tr>
            <td>Input word :</td>
            <td><input type="text" name="word" /></td>
        </tr>
        <tr>
            <td>Input article id :</td>
            <td><input type="text" name="id"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/></form>
</body>
</html>
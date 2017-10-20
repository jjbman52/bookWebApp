<%-- 
    Document   : authorAdd
    Created on : Oct 14, 2017, 11:15:05 AM
    Author     : jordanrehbein
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
        <title>Add Author</title>
    </head>
    <div id="backround">
        <body>
            <div class="body">
                <h1>Add Author</h1>
                <table border="1">
                    <c:forEach var = "a" items="${authorList}">
                        <tr>
                            <td>${a.authorId}</td>
                            <td>${a.authorName}</td>
                            <td><fmt:formatDate pattern = "yyy-MM-dd" value = "${a.dateAdded}" /></td>
                            <td><input type="button" value="Edit" onclick="location.href = 'authorController?action=editDelete&id=${a.authorId}'" /></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <form name="form1" id="form1" method="POST" action="authorController?action=save">
                    <table border="1">
                        <tr>
                            <td>Enter New Name:</td> <td><input type="text" value="" name="name"></td>
                            <td><input type="submit" name="submit" value="Add"></td>
                        </tr>
                    </table>
                </form>
                <br>
                <input type="button" value="Back" onclick="location.href = 'authorController?action=list'" />
                <jsp:include page="footer.jsp" />
            </div>
        </body>
    </div>
</html>

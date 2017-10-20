<%-- 
    Document   : authorList
    Created on : Sep 16, 2017, 10:59:52 AM
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
        <title>Author List</title>
    </head>
    <div id="backround">
        <body>
            <div class="body">
                <h1>Author List</h1>
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
                <input type="button" value="Add Author" onclick="location.href = 'authorController?action=add'" />
                <jsp:include page="footer.jsp" />
            </div>
        </body>
    </div>
</html>

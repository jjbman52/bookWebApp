<%-- 
    Document   : authorEdit
    Created on : Oct 12, 2017, 6:09:07 PM
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
                <h1>Edit Author</h1>
                <table>
                    <tr>
                        <td >${author.authorId}</td>
                        <td>${author.authorName}</td>
                        <td><fmt:formatDate pattern = "yyy-MM-dd" value = "${author.dateAdded}" /></td>
                        <td><input type="button" value="Delete" onclick="location.href = 'authorController?action=delete&id=${author.authorId}'" /></td>
                    </tr>
                </table>
                <br>
                <form name="form" id="form" method="POST" action="authorController?action=save&id=${author.authorId}">
                    <table border="1">
                        <tr>
                            <td>Enter New Name:</td> <td><input type="text" value="" name="name"></td>
                            <td>Enter New Date:</td> <td><input type="text" value="" name="date"></td>
                            <td><input type="submit" name="submit" value="Save"></td>
                        </tr>
                    </table>
                </form>
                <br>
                <input type="button" value="Back" onclick="location.href = 'authorController?action=list'" />
            </div>
        </body>
    </div>
</html>


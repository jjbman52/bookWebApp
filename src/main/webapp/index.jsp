<%-- 
    Document   : index
    Created on : Sep 16, 2017, 10:50:21 AM
    Author     : jordanrehbein
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
        <title>Book Web Application</title>
    </head>
    <div id="backround">
        <body>
            <div class="body">
                <h1>Pick a Task</h1>
                <ul>
                    <li><a href="authorController?action=list">View all Authors</a></li>
                    <li>More to Come</li>
                </ul>
                <jsp:include page="footer.jsp" />
            </div>
        </body>
    </div>
</html>

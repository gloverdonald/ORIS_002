
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        .avatar {
            width: 100px;
            height: 100px;
            display: inline;
            border-radius: 50px;
            justify-content: center;
        }
        .name {
            display: inline;
        }
    </style>
</head>
<body>
<h1>Profile</h1>
<%if(request.getSession().getAttribute("avatarId")==null){%>
<img class="avatar" alt="IMAGE" src="no-avatar.png" />
<% } else { %>
<img class="avatar" alt="IMAGE" src="files/<%=request.getSession().getAttribute("avatarId")%>" />
<% }
%>
<form action="/file-upload">
    <button style="margin-top: 10px">Upload avatar</button>
</form>
<div class="name"> <%=request.getSession().getAttribute("User")%></div>
<form action="/sign-out">
    <button style="margin-top: 10px">Exit</button>
</form>
</body>
</html>

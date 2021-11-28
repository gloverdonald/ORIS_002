<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/profile.js"></script>
    <style>
        .authorName {
            color: #170000;
        }
    </style>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <form id="add-post-form" action="/add-post" method="post">
                <label>
                    Ваша запись:
                    <textarea class="input_green" required id="content" name="content"></textarea>
                </label>
                <input class="button1" type="submit">
            </form>

            <div class="divider"></div>

            <div id="post-list">
                <#list feedPosts as post>
                    <div class="post-${post.id?c}">
                        <div class="light_blue text">${post.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                        <div class="text">Автор: <a class="authorName decoration" href="/user-profile?id=${post.author.id}">${post.author.firstName}  </a></div>
                        <div class="text">${post.content}</div>
                        <#if (user.id == post.author.id)>
                            <input class="deleteButton" name="delid" value="delete" type="submit" onclick="postDelete((${post.id?c}))">
                        </#if>

                        <#if post_index < feedPosts?size - 1>
                            <div class="divider"></div>
                        </#if>
                    </div>
                </#list>
                <div style="text-align: center">Всего <span id="postsCount">${feedPosts?size}</span> записей</div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

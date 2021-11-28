<!DOCTYPE html>
<html lang="en">
<head>



    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link rel="shortcut icon" href="/iconpic.ico" type="image/x-icon">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/profile.js"></script>

    <style>
        .followsButton {
            background-color: #FF4500;
            color: white;
            display: inline-block;
            padding: 6px 12px;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            cursor: pointer;
            border: 1px solid transparent;
            border-radius: 4px;
            text-decoration: none;
            margin: 3px 2px 0 2px;
        }

        .followsButton:hover {
            background-color: #FF7744;
            border-color: #C15932;
        }
    </style>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Профиль</div>
            <div class="white-container">

                <#if user.avatarId??>
                    <img class="user-avatar" alt="IMAGE" src="/files/${user.avatarId}"/>
                <#else>
                    <img class="user-avatar" alt="IMAGE" src="/no-avatar.png"/>
                </#if>

                <div class="user-info-text">
                    <div class="user-info">${user.firstName}</div>
                    <div class="user-info">${user.lastName}</div>
                    <div class="user-info">${user.email}</div>
                </div>
                <form action="/file-upload">
                    <button style="margin-top: 10px">Upload avatar</button>
                </form>
                <div class="user-info">
                    <a href="/followers?id=${user.id}" class="followsButton">${followersCount} в читателях</a>
                    <a href="/following?id=${user.id}" class="followsButton">${followingCount} в читаемых</a>
                </div>

            </div>

            <form id="add-post-form" action="/add-post" method="post">
                <label>
                    Ваша запись:
                    <textarea class="input_green" required id="content" name="content"></textarea>
                </label>
                <input class="button1" type="submit">
            </form>

            <div class="divider"></div>

            <div id="post-list">
                <#list posts as post>
                    <div class="post-${post.id?c}">
                        <div class="light_blue text">${post.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                        <div class="text">Автор: ${post.author.firstName ! ""}</div>
                        <div class="text">${post.content}</div>

                        <input class="deleteButton" name="delid" value="delete" type="submit" onclick="postDelete((${post.id?c}))">
                        <div class="divider"></div>

                    </div>
                </#list>
                <div style="text-align: center" >Всего <span id="postsCount"  class="postsCount" >${postsCountQ}</span> записей</div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

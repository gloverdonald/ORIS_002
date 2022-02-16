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
    <script>
        let isFollower = '${isFollower}'
        let follws = ${followersCount}


        function switchSubscription(profileId) {
            console.log(isFollower)
            let buttonsScore = $('.button-score')

            if (isFollower === 'unfollow') {
                follws = follws - 1
                unsubscribe(profileId)
            } else {
                follws = follws + 1
                subscribe(profileId)
            }
            buttonsScore.html(follws + " в читателях")
        }

        function subscribe(profileId) {
            console.log(profileId)

            $.ajax("/follow", {
                method: 'POST',
                data: "follow=" + profileId,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },

                success: function (data) {
                    isFollower = 'unfollow';
                    console.log('SUBSCRIBED TO ' + profileId)
                    let buttons = $('.subscribe-button-' + profileId)
                    buttons.html("unfollow")
                }
            })
        }

        function unsubscribe(profileId) {

            console.log(profileId)
            $.ajax("/follow?follow=" + profileId, {
                method: 'DELETE',
                success: function (data) {
                    isFollower = 'follow';
                    /*subscriptions.pop(profileId)*/
                    console.log('UNSUBSCRIBED FROM ' + profileId)
                    let buttons = $('.subscribe-button-' + profileId)
                    buttons.html("follow")
                }
            })
        }


    </script>
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


        .followsButtonAcss{
            width: 100px;
            background-color: #002aff;
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
        .followsButtonAcss:hover {
            background-color: #3356ff;
            border-color: #23348a;
        }
    </style>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Профиль юзера</div>
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
                    <a href="/followers?id=${user.id}" class="followsButton button-score">${followersCount} в
                        читателях</a>
                    <a href="/following?id=${user.id}" class="followsButton ">${followingCount} в читаемых</a>
                </div>
                <#--<div class="user-info followsButton">-->
                    <button class=" followsButtonAcss subscribe-button subscribe-button-${user.id}"
                            onclick="switchSubscription(${user.id})">${isFollower}</button>

                <#--</div>-->

            </div>

            <#--<form id="add-post-form" action="/add-post" method="post">
                <label>
                    Ваша запись:
                    <textarea class="input_green" required id="content" name="content"></textarea>
                </label>
                <input class="button1" type="submit">
            </form>-->

            <div class="divider"></div>

            <div id="post-list">
                <#list posts as post>
                    <div>
                        <div class="light_blue text">${post.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                        <div class="text">Автор: ${post.author.firstName ! ""}</div>
                        <div class="text">${post.content}</div>

                        <#if post_index < posts?size - 1>
                            <div class="divider"></div>
                        </#if>
                    </div>
                </#list>
                <div style="text-align: center">Всего <span id="postsCount">${posts?size}</span> записей</div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

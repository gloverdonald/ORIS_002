<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Читатели</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/profile.js"></script>
    <style>

        .list-wrapper {
            max-width: 590px;
            margin: 50px auto;
        }

        .list {
            background: white;
            border-radius: 2px;
            list-style: none;
            padding: 10px 20px;
        }

        .list-item {
            display: flex;
            margin: 10px;
            padding-bottom: 5px;
            padding-top: 5px;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }

        :last-child {
            border-bottom: none;
        }

        .text1 {
            width: 100%;
        }

        .light_blue {
            width: 590px;
        }


    </style>
</head>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="center-content">
        <div class="container">
            <div class="title">Читатели</div>
            <div class="white-container">
                <#if (followingCount != 0)>
                    <div class="list-wrapper" ng-app="app" ng-controller="MainCtrl as ctrl">
                        <ul class="list">
                            <#list following as user1>
                                <li ng-repeat="user in ctrl.users" class="list-item">
                                    <div>
                                        <div class="light_blue text1">
                                            <div class="light_blue text"><#if user1.avatarId??>
                                                    <img class="fol-avatar" alt="IMAGE" src="/files/${user1.avatarId}"/>
                                                <#else>
                                                    <img class="fol-avatar" alt="IMAGE" src="/no-avatar.png"/>
                                                </#if> </div>
                                            <div class="light_blue text">
                                                <a href="/user-profile?id=${user1.id}"> ${user1.firstName} ${user1.lastName}</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>
                <#else>
                    <div class="light_blue text">Нет читателей</div>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
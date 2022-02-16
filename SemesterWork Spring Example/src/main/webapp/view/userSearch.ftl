<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Поиск</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Open+Sans);
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
        .trt{
            height: 50px;
        }
        .light_blue {
            width: 590px;
        }
        .white-container {
            border: 0 double black;
        }



        .search {
            width: 100%;
            position: relative;
            display: flex;
        }

        .searchTerm {
            width: 100%;
            border: 3px solid #139e95;
            border-right: none;
            padding: 5px;
            height: 20px;
            border-radius: 5px 0 0 5px;
            outline: none;
            color: #9DBFAF;
        }

        .searchTerm:focus{
            color: #139e95;
        }

        .searchButton {
            width: 90px;
            height: 36px;
            border: 1px solid #139e95;
            background: #139e95;
            text-align: center;
            color: #fff;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            font-size: 18px;
        }

        /*Resize the wrap to see the search bar change!*/
        .wrap{
            width: 90%;
           /* position: absolute;*/
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/search.js"></script>
</head>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="center-content">
        <div class="container">

            <div class="white-container">
                <div class="list-wrapper" ng-app="app" ng-controller="MainCtrl as ctrl">
                    <form id="search-user-form" class="search wrap" action="/searching-user" method="post">
                        <label for="search"></label><input class="searchTerm pas" type="text" id="search" name="search" placeholder="Поиск пользователя" required="">
                        <button type="submit"  class="searchButton"><i class="<#--fa fa-search-->">Найти</i></button>
                    </form>
                    <ul id="list">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
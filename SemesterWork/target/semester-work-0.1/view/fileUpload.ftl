<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>

        form {
            font-size: 120%;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            color: #333366;
            text-align: center;
            background-color: #717171;
        }
    </style>
</head>
<body>
<form class="inputPhoto" action="/file-upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="File Upload">
</form>
</body>
</html>

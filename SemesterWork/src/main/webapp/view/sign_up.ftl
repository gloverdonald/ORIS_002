<!DOCTYPE html>
<html lang="en">
<head>
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="/resources/css/style1.css" rel="stylesheet" type="text/css" media="all" />
    <link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>
<body>
<div class="main-w3layouts wrapper">
    <h1>Регистрация</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <form method="post"  action="/sign-up">
                <label>
                    <input class="text firstname" type="text" name="firstName" placeholder="Имя" required="">
                </label>
                <label>
                    <input class="text lastname" type="text" name="lastName" placeholder="Фамилия" required="">
                </label>
                <label>
                    <input class="text email" type="email" name="email" placeholder="Почта" required="">
                </label>
                <label>
                    <input class="text age" type="number" name="age" placeholder="Возраст" required="">
                </label>
                <label>
                    <input class="text" type="password" name="password" placeholder="Пароль" required="">
                </label>

                <input class="login-button" type="submit" value="Зарегистрироваться">
            </form>
            <p><#--Don't have an Account?--> <a href="/sign-in">Войти!</a></p>
        </div>
    </div>
</div>
</body>
</html>
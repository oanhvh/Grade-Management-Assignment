
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" href="vendor/bootstrap_login/css/bootstrap.min.css">
        <link rel="stylesheet"  href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet"  href="fonts/iconic/css/material-design-iconic-font.min.css">
        <link rel="stylesheet"  href="vendor/animate/animate.css">
        <link rel="stylesheet"  href="vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet"  href="vendor/select2/select2.min.css">
        <link rel="stylesheet"  href="css/util_login.css">
        <link rel="stylesheet"  href="css/main_login.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg.png');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/FPT_logo_2010.svg/1200px-FPT_logo_2010.svg.png" style="margin-left: 9rem;margin-bottom:2rem;" width="100">
                    <form action="login" method="post" class="login100-form validate-form">
                        <span class="login100-form-title p-b-49">
                            Login
                        </span>
                        <p style="color: red;">${ERROR}</p>
                        <div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired">
                            <span class="label-input100">Username</span>
                            <label>
                                <input class="input100" type="text" name="username" placeholder="Type your username">
                            </label>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <span class="label-input100">Password</span>
                            <label>
                                <input class="input100" type="password" name="password" placeholder="Type your password">
                            </label>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="container-login100-form-btn mt-5">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button type="submit" class="login100-form-btn">
                                    Login
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>

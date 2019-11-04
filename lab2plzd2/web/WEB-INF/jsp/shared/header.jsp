<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="statics/css/main.css"/>
</head>
<body>
<div id="header" class="card-header">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="nav-link" href="/"> Strona główna </a>
            </div>
            <div class="navbar-header">
                <a href="/add.html">Dodaj pojazd</a>
            </div>
            <div class="navbar-header">
                <a href="/vehicleList.html">Lista pojazdów</a>
            </div>
            <security:authorize access="isAnonymous()">
                <div ${param['pageName'] == 'logonForm' ?'class="active"':''}>
                    <a href="/login">Zaloguj się</a>
                </div>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            <div>
                <label style="color:pink; margin-top: 15px;">
                    Cześć <security:authentication property="principal.username"/>!
                </label>
            </div>
            <div>
                <a href="#" onclick="document.getElementById('logout').submit()">Wyloguj się</a>
                <form action="/logout" id="logout" method="post" style="display: none;">
                    <security:csrfInput/>
                </form>
            </div>
                </security:authorize>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav"></ul>
            </div>
        </div>
    </nav>
</div>
</body>
</html>
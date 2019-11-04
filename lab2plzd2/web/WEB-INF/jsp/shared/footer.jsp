<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="footer">
    <div class="container">
        <footer>
            <security:authorize access="isAuthenticated()">
                Takie masz role bro > <br>
                <security:authentication property="principal.authorities"/>
            </security:authorize>
            © 2019 Platformy programowania by Arleta
        </footer>
    </div>
</div>
</body>
</html>


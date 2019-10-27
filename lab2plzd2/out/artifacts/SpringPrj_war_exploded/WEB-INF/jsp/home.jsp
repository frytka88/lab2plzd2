<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="lista"></c:param>
</c:import>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<div id="main" class="container">
    To moja strona domowa
    <br/>
    ${ja} (zadania 2)
    <br/>
    <br/>
    <br/>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>



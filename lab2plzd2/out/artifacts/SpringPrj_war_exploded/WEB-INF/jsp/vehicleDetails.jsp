<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="shared/header.jsp">
    <%--    <jsp:param name="pageName" value="vehicleDetails"></jsp:param>--%>
</c:import>
<html>
<head>
    <security:authorize access="isAuthenticated()">
        <title>Szczegóły Pojazdu</title>
    </security:authorize>
</head>
<body>
<div id="main">
    <security:authorize access="isAnonymous()">
        <p></p>
        <center>
            <div ${param['pageName'] == 'logonForm' ?'class="active"':''}>
                <b> Nie masz dostępu do tych danych, zaloguj się!</b> <br>
                <a href="/login">Zaloguj się</a>
            </div>
        </center>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <H1>Dane pojazdu</H1>
        Id: <b>${mojSamochod.id}</b><br/>
        Model: <b>${empty mojSamochod.model?'Brak danych': mojSamochod.model}</b><br/>
        Name: <b>${empty mojSamochod.name? 'Brak danych': mojSamochod.name}</b><br/>
        Date: <fmt:formatDate value="${mojSamochod.productionDate}" type="date" timeStyle="medium"/><br/>
        Price: <b><fmt:formatNumber type="CURRENCY" value="${mojSamochod.price}" currencySymbol="PLN"/></b><br/>
        <br/>

        <a class="btn btn-info" href="/">Wstecz</a>
    </security:authorize>
</div>

</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="shared/header.jsp"></jsp:include>
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="lista"></c:param>
</c:import>
<html>
<head>
    <title>Lista pojazdów</title>
</head>
<body>
<div id="main">
    <H1>LISTA POJAZDOW</H1>

    <c:if test="${empty lista}">
        Lista pojazdów jest pusta
    </c:if>

    <c:if test="${not empty lista}">
        Lista zawiera ${fn:length(lista)} pojazdów
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Marka</th>
                <th>Model</th>
                <th>Data produkcji</th>
                <th>Cena</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="pojazd">
                <tr>
                    <td>
                        <a href="?id=${pojazd.id}">${pojazd.name}</a>
                    </td>
                    <td>${empty pojazd.model?'Brak danych': pojazd.model}</td>
                    <td><fmt:formatDate value="${pojazd.productionDate}" type="date" timeStyle="medium"/></td>
                    <td><fmt:formatNumber type="CURRENCY" value="${pojazd.price}" currencySymbol="PLN"/></td>
                    <security:authorize access="hasRole('ADMIN')">
                        <td>
                            <a class="btn btn-primary" href="/add.html?id=${pojazd.id}">Edytuj</a>
                            <a class="btn btn-danger" href="?rId=${pojazd.id}">Usuń</a>
                        </td>
                    </security:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:set var="page" value="${lista}" scope="request"/>
        <c:set var="mainUrl" value="vehicleList.html" scope="request"/>
        <jsp:include page="shared/pagination.jsp"/>
    </c:if>
    <security:authorize access="hasRole('ADMIN')">
        <al>
            <button type="button" class="btn btn-info" onclick="window.location.href = '/add.html'"> Dodaj pojazd
            </button>
        </al>
    </security:authorize>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>
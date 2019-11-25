<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="vehicleList"></c:param>
</c:import>
<html>
<head>
    <title>Lista pojazdów</title>
</head>
<body>
<div id="main" class="container">
    <H1>LISTA POJAZDOW</H1>

    <form:form id="searchForm" modelAttribute="searchCommand">
        <div class="row">
            <div class="form-group col-md-6">
                <label for="phrase">Podaj szukana fraze: </label>
                <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group col-md-3">
                <label for="phrase">Cena od:</label>
                <form:input path="minPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="minPrice" cssClass="error text-danger" element="div"/>
            </div>
            <div class="form-group col-md-3">
                <label for="phrase">Cena do:</label>
                <form:input path="maxPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="maxPrice" cssClass="error text-danger" element="div"/>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-8"></div>

            <div class="form-group col-md-2">
                <a href="/vehicleList.html?all" class="btn btn-success">
                    <span class="glyphicon glyphicon-refresh"></span> Pokaż wszystko
                </a>
            </div>

            <div class="form-group col-md-2">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Wyszukaj
                </button>
            </div>
        </div>
    </form:form>

    <c:if test="${empty vehicleListPageable.content}">
        ${searchCommand.isEmpty() ? 'Lista pojazdów jest pusta' : 'Brak wyników'}
    </c:if>

    <c:if test="${not empty vehicleListPageable.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${vehicleListPageable.totalElements} pojazdów
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${vehicleListPageable.totalElements} pojazdów
            </c:otherwise>
        </c:choose>
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
            <c:forEach items="${vehicleListPageable.content}" var="pojazd">
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

        <c:set var="page" value="${vehicleListPageable}" scope="request"/>
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
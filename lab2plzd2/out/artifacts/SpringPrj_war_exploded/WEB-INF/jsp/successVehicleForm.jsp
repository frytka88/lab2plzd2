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
    <title>Poprawnie dodany pojazd</title>
</head>
<body>
<div id="main">
    <H1>Poprawnie dodany pojazd</H1>

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
    </c:if>
    <br>
    <br>
        <al>
            <button type="button" class="btn btn-info" onclick="window.location.href = 'vehicleList.html'"> Wróc do listy pojazdów
            </button>
        </al>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>
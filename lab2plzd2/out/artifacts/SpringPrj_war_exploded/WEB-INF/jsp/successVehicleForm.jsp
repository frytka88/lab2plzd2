<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="shared/header.jsp"></jsp:include>
<html>
<head>
    <title>Poprawnie dodanie pojazdu</title>
</head>
<div id="main">
    <H1>LISTA POJAZDOW</H1>
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
                    <td>
<%--                        <button type="button" class="btn btn-primary">Edytuj</button>--%>
                        <a class="btn btn-danger" href="vehicleForm.html?id=${pojazd.id}">Edytuj</a>
                        <a class="btn btn-danger" href="?id=${pojazd.id}">Usuń</a>
                            <%--                        <button type="button" class="btn btn-danger"">Usuń</button>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <al>
        <button type="button" class="btn btn-info" onclick="window.location.href = '/vehicleList.html'"> Wyświetl liste pojazdów </button>
    </al>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>
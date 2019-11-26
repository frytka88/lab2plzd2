<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="lista"></c:param>
</c:import>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dodawanie pojazdu</title>
</head>
<body>
<p></p>
<div id="main" class="container">
    <form:form modelAttribute="vehicle">
        <div class="form-group">
            <label for="name">Nazwa:</label>
            <form:input path="name" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <label for="model">Model:</label>
            <form:input path="model" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <label for="price">Cena:</label>
            <form:input path="price" cssClass="form-control" type="" cssErrorClass="form-control is-invalid"/>
            <form:errors path="price" cssClass="error text-danger" element="div"/>
        </div>
        <div class="form-group">
            <label for="productionDate">Data Produkcji:</label>
            <form:input path="productionDate" cssClass="form-control" type="date" cssErrorClass="form-control is-invalid"/>
            <form:errors path="productionDate" cssClass="error text-danger" element="div"/>
        </div>

        <div class="form-group">
            <label for="vehicleType.id">Typ Pojazdu: </label>
            <form:select path="vehicleType.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
                <form:option value="-1"> Wybierz typ pojazdu </form:option>
                <form:options items="${vehicleTypes}" itemLabel="name" itemValue="id"/>
            </form:select>
            <form:errors path="vehicleType.id" cssClass="error text-danger" element="div"/>
        </div>

        <button type="submit" class="btn btn-success" class="btn btn-success">Wyślij</button>
    </form:form>
    <br>
    <button type="button" class="btn btn-dark" onclick="window.location.href = '/vehicleList.html'"> Cofnij</button>
    </br>
</div>
</body>
</div>
</html>
<jsp:include page="shared/footer.jsp"/>
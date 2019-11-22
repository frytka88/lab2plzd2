<%@ c:taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="firstPageUrl" value="${mainUrl}?page=0&size=${page.size}"/>
<c:url var="prevPageUrl" value="${mainUrl}?page=${page.number - 1}&size=${page.size}"/>
<c:url var="nextPageUrl" value="${mainUrl}?page=${page.number + 1}&size=${page.size}"/>
<c:url var="lastPageUrl" value="${mainUrl}?page=${page.totalPages - 1}&size=${page.size}"/>

<nav>
    <ul class="pagination">

        <li ${page.first?'class="disabled"':''}>
            <a href="${page.first?'#':firstPageUrl}">
                <span>Pierwsza</span>
            </a>
        </li>

        <li ${page.first?'class="disabled"':''}>
            <a href="${page.first?'#':prevPageUrl}">
                <span>&laquo;</span>
            </a>
        </li>

        <c:forEach var="pageIdx" begin="${0}" end="${page.totalPages-1}">
            <c:url var="pageUrl" value="${mainUrl}?page=${pageIdx}&size=${page.size}"/>
            <li ${pageIdx == page.number?'class="active"':''}>
                <a href="${pageUrl}">${pageIdx+1}</a>
            </li>
        </c:forEach>

        <li ${page.last?'class="disabled"':''}>
            <a href="${page.last?'#':nextPageUrl}">
                <span>&raquo;</span>
            </a>
        </li>

        <li ${page.last?'class="disabled"':''}>
            <a href="${page.last?'#':lastPageUrl}">
                <span>Ostatnia</span>
            </a>
        </li>

    </ul>

    <ul class="pagination" style="float: right">
        <c:set var="pageSizes" value="${[1, 2, 3, 4, 20]}"/>
        <c:forEach var="size" items="${pageSizes}">
            <c:url var="pageUrl" value="${mainUrl}?page=0&size=${size}"/>
            <li ${page.size eq size?'class="active"':''}><a href="${pageUrl}"><span>${size}</span></a></li>
        </c:forEach>
    </ul>
</nav>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tag:layout>
    <div id="mainPage">
        <div class="container">
            <div style="margin-left: 5%; margin-right: 5%;">
                <jsp:include page="searchFilter.jsp"/>
                <jsp:include page="searchTable.jsp"/>
            </div>
        </div>
    </div>
</tag:layout>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="messages.create" var="createMessage"/>

<tag:layout>
    <div id="mainPage">
        <jsp:include page="commons/header.jsp"/>
        <div class="container">
            <div style="margin-left: 5%; margin-right: 5%;">
                <div class="row">
                    <div class="col-md-9">

                    </div>
                </div>
                <jsp:include page="search/searchFilter.jsp"/>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <input class="btn btn-success" style="margin-top: 25px" value="${createMessage}" onclick="window.location.href = '/create/';">
                </sec:authorize>
                <jsp:include page="search/searchTable.jsp"/>
            </div>
        </div>
        <jsp:include page="commons/footer.jsp"/>
    </div>
</tag:layout>
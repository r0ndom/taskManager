<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.description" var="descriptionMessage"/>
<spring:message code="messages.status" var="statusMessage"/>
<spring:message code="messages.author" var="authorMessage"/>
<spring:message code="messages.executor" var="executorMessage"/>
<spring:message code="messages.expectedTime" var="timeMessage"/>
<spring:message code="messages.edit" var="editMessage"/>
<spring:message code="messages.back" var="backMessage"/>

<tag:layout>
    <div id="mainPage">
        <jsp:include page="../commons/header.jsp"/>
        <div class="container">
            <c:forEach items="${taskData}" var="item">
                <c:if test="${item.readable && !item.writable}">
                    <div class="form-group">
                        <label>${item.name}</label>
                        <p class="form-control-static">${item.value}</p>
                    </div>
                </c:if>
                <c:if test="${item.readable && item.writable}">
                    <div class="form-group">
                        <label>${item.name}</label>
                        <input class="form-control-static" value="${item.value}"/>
                    </div>
                </c:if>
            </c:forEach>
            <c:if test="${isWritable}">
                <button class="btn btn-success" onclick="window.location.href ='/app/tasks/${taskData[0].id}/'">${editMessage}</button>
            </c:if>
            <button class="btn btn-default" onclick="window.location.href ='/app/tasks/'">${backMessage}</button>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>




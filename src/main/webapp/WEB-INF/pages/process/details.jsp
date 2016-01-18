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
                <div class="form-group">
                    <label>${nameMessage}</label>
                    <p class="form-control-static" >${taskData.name}</p>
                </div>
                <div class="form-group">
                    <label>${descriptionMessage}</label>
                    <p class="form-control-static">${taskData.description}</p>
                </div>
                <div class="form-group">
                    <label>${statusMessage}</label>
                    <p class="form-control-static">${taskData.status}</p>
                </div>
                <div class="form-group">
                    <label>${authorMessage}</label>
                    <p class="form-control-static">${taskData.author}</p>
                </div>
                <div class="form-group">
                    <label>${executorMessage}</label>
                    <p class="form-control-static">${taskData.executor}</p>cd
                </div>
                <div class="form-group">
                    <label>${timeMessage}</label>
                    <p class="form-control-static">${taskData.expectedTime}</p>
                </div>
                <c:if test="${isWritable}">
                    <button class="btn btn-success"
                            onclick="window.location.href ='/app/tasks/${taskData.id}/'">${editMessage}</button>
                </c:if>
                <button class="btn btn-default" onclick="window.location.href ='/app/tasks/'">${backMessage}</button>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>




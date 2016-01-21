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
<spring:message code="messages.next" var="nextMessage"/>

<tag:layout>
    <div id="mainPage">
        <jsp:include page="../commons/header.jsp"/>
        <div class="container">
            <form:form method="POST" commandName="formData" action="/app/tasks/submitTaskForm">
                <c:forEach items="${taskData}" var="item">
                    <c:choose>
                        <c:when test="${item.type == 'enum'}">
                            <label>${item.name}</label>
                            <form:select path="map['${item.id}']">
                                <form:options items="${item.selectValues}"/>
                            </form:select>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${isWritable}">
                                    <c:if test="${!item.writable}">
                                        <div class="form-group">
                                            <label>${item.name}</label>
                                            <form:input path="map['${item.id}']" class="form-control-static"
                                                        value="${item.value}"
                                                        disabled="true"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${item.writable}">
                                        <div class="form-group">
                                            <label>${item.name}</label>
                                            <form:input path="map['${item.id}']" class="form-control-static"
                                                        value="${item.value}"/>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label>${item.name}</label>
                                        <form:input path="map['${item.id}']" class="form-control-static" value="${item.value}"
                                                    disabled="true"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <form:hidden path="id" class="form-control-static" value="${taskId}"/>
                <c:if test="${isSubmit}">
                    <input class="btn btn-success" value="${nextMessage}" type="submit"/>
                </c:if>
                <c:if test="${isEditor}">
                    <input class="btn btn-success" onclick="window.location.href ='/app/tasks/${taskId}/'"
                           value="${editMessage}"/>
                </c:if>
                <input class="btn btn-default" onclick="window.location.href ='/app/tasks/'" value="${backMessage}"/>
            </form:form>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>




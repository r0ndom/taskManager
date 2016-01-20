<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.description" var="descriptionMessage"/>
<spring:message code="messages.create" var="createMessage"/>
<spring:message code="messages.reset" var="resetMessage"/>

<tag:layout>
    <div id="mainPage">
        <jsp:include page="../commons/header.jsp"/>
        <div class="container">
            <div style="margin-left: 5%; margin-right: 5%;">
                <form:form method="POST" commandName="formData" action="/app/tasks/submitTaskForm">
                    <c:forEach items="${taskData}" var="item">
                        <c:if test="${!item.writable}">
                            <div class="form-group">
                                <label>${item.name}</label>
                                <form:input path="map['${item.id}']" class="form-control-static" value="${item.value}"
                                            disabled="true"/>
                            </div>
                        </c:if>
                        <c:if test="${item.writable}">
                            <div class="form-group">
                                <label>${item.name}</label>
                                <form:input path="map['${item.id}']" class="form-control-static" value="${item.value}"/>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div hidden class="form-group">
                        <form:input path="id" class="form-control-static" value="${taskId}"/>
                    </div>
                    <button type="submit" class="btn btn-success">${createMessage}</button>
                    <button type="reset" class="btn btn-default">${resetMessage}</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>
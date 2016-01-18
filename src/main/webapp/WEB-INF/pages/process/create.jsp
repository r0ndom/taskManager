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
                    <div class="form-group">
                        <label>${nameMessage}</label>
                        <form:input path="map['name']" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>${descriptionMessage}</label>
                        <form:textarea path="map['description']" cssClass="form-control"/>
                    </div>
                    <div style="display: none;">
                        <form:hidden path="map['author']" value="${author}"/>
                    </div>
                    <button type="submit" class="btn btn-success">${createMessage}</button>
                    <button type="reset" class="btn btn-default">${resetMessage}</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>
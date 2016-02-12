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
                        <c:choose>
                            <c:when test="${item.type == 'enum'}">
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <label>${item.name}</label>
                                    </div>
                                    <div class="col-md-4">
                                        <form:select path="map['${item.id}']" cssClass="form-control">
                                            <form:options items="${item.selectValues}"/>
                                        </form:select>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${isWritable}">
                                        <c:if test="${!item.writable}">
                                            <div class="form-group row">
                                                <div class="col-md-2">
                                                    <label>${item.name}</label>
                                                </div>
                                                <div class="col-md-4">
                                                    <form:input path="map['${item.id}']" class="form-control"
                                                                value="${item.value}"
                                                                disabled="true"/>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${item.writable}">
                                            <div class="form-group row">
                                                <div class="col-md-2">
                                                    <label>${item.name}</label>
                                                </div>
                                                <div class="col-md-4">
                                                    <form:input path="map['${item.id}']" class="form-control"
                                                                value="${item.value}"/>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group row">
                                            <div class="col-md-2">
                                                <label>${item.name}</label>
                                            </div>
                                            <div class="col-md-4">
                                                <form:input path="map['${item.id}']" class="form-control"
                                                            value="${item.value}"
                                                            disabled="true"/>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <div hidden class="form-group">
                        <form:input path="id" class="form-control" value="${taskId}"/>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2">
                            <label>Executors:</label>
                        </div>
                        <div class="col-md-4">
                            <form:select path="map['executor']" cssClass="form-control" itemValue="ldap">
                                <form:options items="${userList}"/>
                            </form:select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">${createMessage}</button>
                    <button type="reset" class="btn btn-default">${resetMessage}</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>
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
<spring:message code="messages.delete" var="deleteMessage"/>
<spring:message code="messages.editDesc" var="editDesc"/>

<tag:layout>
    <div id="mainPage">
        <jsp:include page="../commons/header.jsp"/>
        <div class="container">
            <form:form method="POST" commandName="formData" action="/app/tasks/submitTaskForm" cssStyle="display: inline;">
                <c:forEach items="${taskData}" var="item">
                    <c:choose>
                        <c:when test="${item.type == 'enum'}">
                            <p></p>

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
                            <p></p>
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
                                                <form:input path="map['${item.id}']" cssClass="form-control"
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
                                                <form:input path="map['${item.id}']" cssClass="form-control"
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
                                            <form:input path="map['${item.id}']" cssClass="form-control"
                                                        value="${item.value}"
                                                        disabled="true"/>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <form:hidden path="id" cssClass="form-control-static" value="${taskId}"/>
                <span>
                    <c:if test="${isSubmit}">
                        <input class="btn btn-success" style="display: inline;" value="${nextMessage}" type="submit"/>
                    </c:if>
                    <c:if test="${isEditor}">
                        <input class="btn btn-success" style="display: inline;" onclick="window.location.href ='/app/tasks/${taskId}/'"
                               value="${editMessage}"/>
                    </c:if>
                    <c:if test="${editDescr}">
                        <form:hidden path="map['edit']" cssClass="form-control-static" value="true"/>
                        <input class="btn btn-success" style="display: inline;" value="${editDesc}" type="submit"/>
                    </c:if>
                </span>
            </form:form>
            <c:if test="${isDeleted}">
                <form action="/app/tasks/delete/${taskId}" method="POST" style="display: inline;margin:0px;padding:0px;">
                    <input type="submit" class="btn btn-danger" value="${deleteMessage}"/>
                </form>
            </c:if>

            <input class="btn btn-default" onclick="window.location.href ='/app/tasks/'" value="${backMessage}" style="display: inline"/>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </div>
</tag:layout>




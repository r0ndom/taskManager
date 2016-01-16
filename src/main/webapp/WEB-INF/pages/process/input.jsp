<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.description" var="descriptionMessage"/>
<spring:message code="messages.create" var="createMessage"/>
<spring:message code="messages.reset" var="resetMessage"/>
<spring:message code="messages.mainPage" var="mainPageMessage"/>
<spring:message code="messages.next" var="nextMessage"/>

<tag:layout>
  <div id="mainPage">
    <jsp:include page="../commons/header.jsp"/>
    <div class="container">
      <div style="margin-left: 5%; margin-right: 5%;">
        <form:form method="POST" commandName="formData" action="/app/tasks/submitTaskForm">
          <div class="row">
            <div class="col-md-2">
              <p>${nameMessage}</p>
              <p>${descriptionMessage}</p>
            </div>
            <div class="col-md-10">
              <form:hidden path="id" value="${taskData.activitiDynamicId}"/>
              <form:input path="map['name']" cssClass="form-control" value="${taskData.name}" readonly="true"/>
              <textarea name="map['description']" class="form-control" readonly>${taskData.description}</textarea>
              <c:if test="${taskData.state.name == 'expectedTime'}">
                <form:input path="map['expectedTime']" cssClass="form-control"/>
                <form:hidden path="map['executor']" value="${executor}"/>
              </c:if>
              <c:choose>
                <c:when test="${taskData.state.name == 'inProgress'}">
                  <form:input path="map['status']" cssClass="form-control" readonly="true" value="development"/>
                </c:when>
                <c:when test="${taskData.state.name == 'onTesting'}">
                  <form:input path="map['status']" cssClass="form-control" readonly="true" value="testing"/>
                </c:when>
                <c:when test="${taskData.state.name == 'setTaskStatus'}">
                  <form:input path="map['status']" cssClass="form-control" readonly="true" value="done"/>
                </c:when>
                <c:otherwise>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
          <input class="btn btn-success" value="${nextMessage}" type="submit"/>
          <input class="btn btn-default" value="${mainPageMessage}" onclick="window.location.href = '/app/tasks/';"/>
        </form:form>
      </div>
    </div>
    <jsp:include page="../commons/footer.jsp"/>
  </div>
</tag:layout>
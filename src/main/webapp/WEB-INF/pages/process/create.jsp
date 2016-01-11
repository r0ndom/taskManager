<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.description" var="descriptionMessage"/>
<spring:message code="messages.create" var="createMessage"/>
<spring:message code="messages.reset" var="resetMessage"/>

<tag:layout>
  <div id="mainPage">
    <jsp:include page="../commons/header.jsp"/>
    <div class="container">
      <div style="margin-left: 5%; margin-right: 5%;">
          <form:form method="POST" commandName="formData" action="/submitTaskForm">
            <div class="row">
              <div class="col-md-2">
                <p>${nameMessage}</p>
                <p>${descriptionMessage}</p>
              </div>
              <div class="col-md-10">
                <form:input path="map['name']" cssClass="form-control"/>
                <form:textarea path="map['description']" cssClass="form-control"/>
              </div>
            </div>
            <span>
              <input class="btn btn-success" value="${createMessage}" type="submit">
              <input class="btn btn-default" value="${resetMessage}" type="reset">
            </span>
          </form:form>
      </div>
    </div>
    <jsp:include page="../commons/footer.jsp"/>
  </div>
</tag:layout>
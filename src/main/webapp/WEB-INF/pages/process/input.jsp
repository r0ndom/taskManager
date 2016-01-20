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
          <c:forEach items="${taskData}" var="item">
            <c:if test="${item.readable && item.writable}">
              <div class="form-group">
                <label>${item.name}</label>
                <p class="form-control-static">${item.value}</p>
              </div>
            </c:if>
            <c:if test="${item.readable && !item.writable}">
              <div class="form-group">
                <label>${item.name}</label>
                <input class="form-control-static" value="${item.value}"/>
              </div>
            </c:if>
          </c:forEach>
          <div style="display: none;">
            <form:hidden path="map['author']" value="${author}"/>
          </div>
          <input class="btn btn-success" value="${nextMessage}" type="submit"/>
          <input class="btn btn-default" value="${mainPageMessage}" onclick="window.location.href = '/app/tasks/';"/>
        </form:form>
      </div>
    </div>
    <jsp:include page="../commons/footer.jsp"/>
  </div>
</tag:layout>
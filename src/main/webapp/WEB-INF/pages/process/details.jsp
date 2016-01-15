<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
    <style>
      label {
        padding-top: 10px;
      }
    </style>
    <div id="wrapper">
      <div class="container">
        <div class="row col-md-6 col-md-offset-4" style="margin-top: 10%">
            <div class="row">
              <div class="col-md-2">
                <label>${nameMessage}</label>
              </div>
              <div class="col-md-6">
                    <span>${taskData.name}</span>
              </div>
            </div>
            <p></p>
            <div class="row">
              <div class="col-md-2">
                <label>${descriptionMessage}</label>
              </div>
              <div class="col-md-6">
                <span>${taskData.description}</span>
              </div>
            </div>
            <p></p>
            <div class="row">
              <div class="col-md-2">
                <label>${statusMessage}</label>
              </div>
              <div class="col-md-6">
                <span>${taskData.status}</span>
              </div>
            </div>
            <p></p>
            <div class="row">
                <div class="col-md-2">
                    <label>${authorMessage}</label>
                </div>
                <div class="col-md-6">
                        <span>${taskData.author}</span>
                </div>
            </div>
            <p></p>
            <div class="row">
                <div class="col-md-2">
                    <label>${executorMessage}</label>
                </div>
                <div class="col-md-6">
                    <span>${taskData.executor}</span>
                </div>
            </div>
            <p></p>
            <div class="row">
                <div class="col-md-2">
                    <label>${timeMessage}</label>
                </div>
                <div class="col-md-6">
                    <span>${taskData.expectedTime}</span>
                </div>
            </div>
            <p></p>
            <c:if test="${isWritable}">
                <input class="btn btn-success" style="margin-top: 25px" value="${editMessage}" onclick="window.location.href = '/app/tasks/${taskData.id}/';">
            </c:if>
            <input class="btn btn-success" style="margin-top: 25px" value="${backMessage}" onclick="window.location.href = '/app/tasks/';">
        </div>
      </div>
    </div>
    <jsp:include page="../commons/footer.jsp"/>
  </div>
</tag:layout>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="messages.status" var="statusMessage"/>
<spring:message code="messages.executor" var="executorMessage"/>
<spring:message code="messages.author" var="authorMessage"/>
<spring:message code="messages.search" var="searchMessage"/>

<form:form method="POST" commandName="taskSearchFilter" action="/">
    <div class="row">
        <div class="col-md-3">
            <label>${statusMessage}:</label>
            <form:select cssClass="form-control" path="status" items="${statusList}" itemValue="name" itemLabel="name"  />
        </div>
        <div class="col-md-3">
            <label>${executorMessage}:</label>
            <form:select cssClass="form-control" path="executor" items="${executorList}" />
        </div>
        <div class="col-md-3">
            <label>${authorMessage}:</label>
            <form:select cssClass="form-control" path="author" items="${authorList}" />
        </div>
        <div class="col-md-3">
            <input class="btn btn-success" style="margin-top: 25px" value="${searchMessage}" type="submit">
        </div>
    </div>
</form:form>
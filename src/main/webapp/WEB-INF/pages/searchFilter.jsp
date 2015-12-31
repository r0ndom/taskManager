<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="POST" commandName="taskSearchFilter" action="/">
    <div class="row">
        <div class="col-md-3">
            <label>Status:</label>
            <form:select cssClass="form-control" path="status" items="${statusList}" />
        </div>
        <div class="col-md-3">
            <label>Executor:</label>
            <form:select cssClass="form-control" path="executor" items="${executorList}" />
        </div>
        <div class="col-md-3">
            <label>Author:</label>
            <form:select cssClass="form-control" path="author" items="${authorList}" />
        </div>
        <div class="col-md-3">
            <input class="btn btn-success" style="margin-top: 25px" value="Search" type="submit">
        </div>
    </div>
</form:form>
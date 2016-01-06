<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.status" var="statusMessage"/>
<spring:message code="messages.executor" var="executorMessage"/>
<spring:message code="messages.author" var="authorMessage"/>

<table class="table">
    <tr>
        <td>${nameMessage}</td>
        <td>${statusMessage}</td>
        <td>${executorMessage}</td>
        <td>${authorMessage}</td>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr id="${task.id}">
            <td id="name">${task.name}</td>
            <td id="status">${task.status}</td>
            <td id="executor">${task.executor}</td>
            <td id="author">${task.author}</td>
        </tr>
    </c:forEach>
</table>
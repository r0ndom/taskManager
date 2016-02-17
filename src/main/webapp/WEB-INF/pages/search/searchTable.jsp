<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="messages.name" var="nameMessage"/>
<spring:message code="messages.status" var="statusMessage"/>
<spring:message code="messages.executor" var="executorMessage"/>
<spring:message code="messages.author" var="authorMessage"/>
<spring:message code="messages.priority" var="priorityMessage"/>

<table class="table">
    <tr>
        <td>${nameMessage}</td>
        <td>${statusMessage}</td>
        <td>${executorMessage}</td>
        <td>${authorMessage}</td>
        <td>${priorityMessage}</td>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr id="${task.id}" onclick="window.location.href = '/app/tasks/show/${task.id}/'">
            <td id="name">${task.params['name']}</td>
            <td id="status">${task.params['status']}</td>
            <td id="executor">${task.params['executor']}</td>
            <td id="author">${task.params['author']}</td>
            <td id="priority">${task.params['priority']}</td>
            <span id="${task.activitiDynamicId}" hidden></span>
        </tr>
    </c:forEach>
</table>
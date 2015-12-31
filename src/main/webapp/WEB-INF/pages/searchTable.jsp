<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <td>Name</td>
        <td>Status</td>
        <td>Executor</td>
        <td>Author</td>
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
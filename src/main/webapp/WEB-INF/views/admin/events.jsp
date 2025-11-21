<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Events</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>Events</h2>

<c:if test="${param.added == '1'}"><p style="color:green">Event added.</p></c:if>
<c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>
<c:if test="${param.updated == '1'}"><p style="color:green">Event updated.</p></c:if>
<c:if test="${param.deleted == '1'}"><p style="color:green">Event deleted.</p></c:if>

<table border="1" style="border-collapse: collapse;">
  <tr>
    <th>ID</th><th>Category</th><th>Title</th><th>Venue</th>
    <th>Date</th><th>Price</th><th>Seats</th><th>Status</th><th>Actions</th>
  </tr>
  <c:forEach var="e" items="${events}">
    <tr>
      <td>${e.id}</td><td>${e.categoryName}</td><td>${e.title}</td>
      <td>${e.venue}</td><td>${e.eventDate}</td><td>${e.price}</td>
      <td>${e.seatsAvailable}/${e.seatsTotal}</td><td>${e.status}</td>
      <td>
        <a href="${pageContext.request.contextPath}/admin/event/edit?id=${e.id}">Edit</a>
        <form method="post" action="${pageContext.request.contextPath}/admin/event/delete" style="display:inline" onsubmit="return confirm('Delete this event?');">
          <input type="hidden" name="id" value="${e.id}">
          <button type="submit">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/admin/event/new">Create New Event</a></p>

<table border="1" style="border-collapse: collapse;">
  <tr>
    <th>ID</th><th>Category</th><th>Title</th><th>Venue</th>
    <th>Date</th><th>Price</th><th>Seats</th><th>Status</th>
  </tr>
  <c:forEach var="e" items="${events}">
    <tr>
      <td>${e.id}</td>
      <td>${e.categoryName}</td>
      <td>${e.title}</td>
      <td>${e.venue}</td>
      <td>${e.eventDate}</td>
      <td>${e.price}</td>
      <td>${e.seatsAvailable}/${e.seatsTotal}</td>
      <td>${e.status}</td>
    </tr>
  </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/home">Home</a></p>
</body>
</html>

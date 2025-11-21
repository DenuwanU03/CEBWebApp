<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Edit Event</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>Edit Event</h2>

<c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/event/update">
  <input type="hidden" name="id" value="${event.id}">
  <p>
    <label>Category:
      <select name="categoryId">
        <c:forEach var="c" items="${categories}">
          <option value="${c.id}" ${c.id == event.categoryId ? 'selected' : ''}>${c.name}</option>
        </c:forEach>
      </select>
    </label>
  </p>

  <p><label>Title: <input type="text" name="title" required maxlength="120" value="${event.title}"></label></p>
  <p><label>Description: <input type="text" name="description" maxlength="255" value="${event.description}"></label></p>
  <p><label>Venue: <input type="text" name="venue" required value="${event.venue}"></label></p>

  <%
    // Build value for datetime-local (yyyy-MM-ddTHH:mm)
    java.time.LocalDateTime dt = (java.time.LocalDateTime) request.getAttribute("eventDateTmp");
  %>
  <p>
    <label>Date/Time:
      <input type="datetime-local" name="eventDate"
             value="${event.eventDate.toString().substring(0,16)}" required>
    </label>
  </p>

  <p><label>Price: <input type="number" name="price" step="0.01" min="0" value="${event.price}"></label></p>
  <p><label>Total Seats: <input type="number" name="seatsTotal" min="0" value="${event.seatsTotal}"></label></p>
  <p><label>Available Seats: <input type="number" name="seatsAvailable" min="0" value="${event.seatsAvailable}"></label></p>
  <p>
    <label>Status:
      <select name="status">
        <option value="ACTIVE" ${event.status=='ACTIVE' ? 'selected' : ''}>ACTIVE</option>
        <option value="CLOSED" ${event.status=='CLOSED' ? 'selected' : ''}>CLOSED</option>
      </select>
    </label>
  </p>

  <p>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/admin/events">Cancel</a>
  </p>
</form>
</body>
</html>

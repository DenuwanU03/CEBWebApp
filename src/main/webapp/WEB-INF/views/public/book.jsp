<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Book Tickets</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar_public.jsp"/>

<h2>Book Tickets</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<p><strong>${event.title}</strong> — ${event.venue}</p>
<p>Date: ${event.eventDate} | Price: ${event.price}</p>
<p>Available: ${event.seatsAvailable} / ${event.seatsTotal}</p>

<form method="post" action="${pageContext.request.contextPath}/book/create">
  <input type="hidden" name="eventId" value="${event.id}">
  <label>Quantity:
    <input type="number" name="qty" min="1" max="${event.seatsAvailable}" value="1" required>
  </label>
  <button type="submit">Confirm Booking</button>
  <a href="${pageContext.request.contextPath}/events">Cancel</a>
</form>
</body>
</html>

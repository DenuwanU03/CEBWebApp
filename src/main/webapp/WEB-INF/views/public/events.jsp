<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Upcoming Events</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar_public.jsp"/>

<h2>Upcoming Events</h2>
<c:if test="${param.booked == '1'}"><p style="color:green">Booking successful!</p></c:if>
<c:if test="${param.notfound == '1'}"><p style="color:red">Event not found.</p></c:if>

<table border="1" style="border-collapse: collapse;">
  <tr>
    <th>Category</th><th>Title</th><th>Venue</th><th>Date</th>
    <th>Price</th><th>Seats</th><th></th>
  </tr>
  <c:forEach var="e" items="${events}">
    <tr>
      <td>${e.categoryName}</td>
      <td>${e.title}</td>
      <td>${e.venue}</td>
      <td>${e.eventDate}</td>
      <td>${e.price}</td>
      <td>${e.seatsAvailable}/${e.seatsTotal}</td>
      <td>
        <a href="${pageContext.request.contextPath}/book?eventId=${e.id}">Book</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

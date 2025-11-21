<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Campus Event Booking System</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="bg">
  <div class="home-wrap">
    <h1>Campus Event Booking System</h1>
    <p class="tagline">Discover, manage and book university events.</p>

    <ul class="home-menu">
      <li><a href="${pageContext.request.contextPath}/admin/categories">Manage Categories</a></li>
      <li><a href="${pageContext.request.contextPath}/admin/events">Manage Events</a></li>
      <li><a href="${pageContext.request.contextPath}/events">View Upcoming Events (User View)</a></li>
    </ul>
  </div>
</body>
</html>

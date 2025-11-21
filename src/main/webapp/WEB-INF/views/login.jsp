<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login • Campus Event Booking</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <div class="login-box">
    <h2>Campus Event Booking</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">
      <label>Email</label>
      <input type="email" name="email" required>

      <label>Password</label>
      <input type="password" name="password" required>

      <button type="submit">Login</button>
    </form>

    <c:if test="${not empty error}">
      <p class="error" style="margin-top:10px">${error}</p>
    </c:if>

    <p style="margin-top:16px">
      <a href="${pageContext.request.contextPath}/home">Back to Home</a>
    </p>
  </div>
</body>
</html>

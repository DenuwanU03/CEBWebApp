<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<div style="background:#222; color:#fff; padding:10px 14px; display:flex; align-items:center; gap:18px;">
  <a href="${pageContext.request.contextPath}/home" style="color:#fff; font-weight:600;">Home</a>
  <a href="${pageContext.request.contextPath}/admin/categories" style="color:#fff;">Categories</a>
  <a href="${pageContext.request.contextPath}/admin/events" style="color:#fff;">Events</a>
  <a href="${pageContext.request.contextPath}/admin/event/new" style="color:#fff;">Add Event</a>

  <div style="margin-left:auto;">
    <c:choose>
      <c:when test="${not empty sessionScope.userName}">
        <span style="opacity:.8; margin-right:10px;">Hello, ${sessionScope.userName}</span>
        <a href="${pageContext.request.contextPath}/logout" style="color:#fff;">Logout</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login" style="color:#fff;">Login</a>
      </c:otherwise>
    </c:choose>
  </div>
</div>

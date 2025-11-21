<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Edit Category</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Edit Category</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/category/update">
  <input type="hidden" name="id" value="${cat.id}">
  <p>
    <label>Name:
      <input type="text" name="name" required maxlength="60" value="${cat.name}">
    </label>
  </p>
  <p>
    <label>Description:
      <input type="text" name="description" maxlength="255" value="${cat.description}">
    </label>
  </p>
  <p>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/admin/categories">Cancel</a>
  </p>
</form>
</body>
</html>

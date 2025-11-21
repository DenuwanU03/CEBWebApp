<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Categories</title>
  <style>
    body { font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica, Arial, sans-serif; }
    table { border-collapse: collapse; margin-top: 8px; }
    th, td { border: 1px solid #444; padding: 6px 10px; }
    .ok { color: #0a7a0a; }
    .err { color: #b00020; }
    form.inline { display: inline; margin: 0; }
  </style>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>Categories</h2>

<!-- success/error banners -->
<c:if test="${param.added == '1'}"><p class="ok">Category added!</p></c:if>
<c:if test="${param.updated == '1'}"><p class="ok">Category updated.</p></c:if>
<c:if test="${param.notfound == '1'}"><p class="err">Category not found.</p></c:if>
<c:if test="${param.deleted == '1'}"><p class="ok">Category deleted.</p></c:if>
<c:if test="${param.deleted == '0'}"><p class="err">Delete failed.</p></c:if>
<c:if test="${param.inuse == '1'}"><p class="err">Cannot delete: category is used by events.</p></c:if>
<c:if test="${not empty error}"><p class="err">${error}</p></c:if>

<table>
  <tr>
    <th>ID</th><th>Name</th><th>Description</th><th>Actions</th>
  </tr>
  <c:forEach var="c" items="${categories}">
    <tr>
      <td>${c.id}</td>
      <td>${c.name}</td>
      <td>${c.description}</td>
      <td>
        <!-- Edit link (handled by /admin/category/edit GET) -->
        <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.id}">Edit</a>

        <!-- Delete as POST to /admin/category/delete -->
        <form class="inline" method="post"
              action="${pageContext.request.contextPath}/admin/category/delete"
              onsubmit="return confirm('Delete this category?');">
          <input type="hidden" name="id" value="${c.id}">
          <button type="submit">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<hr/>
<h3>Add New Category</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/category/create">
  <p>
    <label>Name:
      <input type="text" name="name" required maxlength="60">
    </label>
  </p>
  <p>
    <label>Description:
      <input type="text" name="description" maxlength="255">
    </label>
  </p>
  <button type="submit">Add</button>
</form>

<p><a href="${pageContext.request.contextPath}/home">Home</a></p>
</body>
</html>

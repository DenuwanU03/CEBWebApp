<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Event</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        // Simple front-end form validation
        function validateEventForm() {
            const title = document.forms["eventForm"]["title"].value.trim();
            const venue = document.forms["eventForm"]["venue"].value.trim();
            const date = document.forms["eventForm"]["eventDate"].value;
            if (title === "" || venue === "" || date === "") {
                alert("Title, venue, and date/time are required.");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <h2>Create New Event</h2>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form name="eventForm" method="post"
          action="${pageContext.request.contextPath}/admin/event/create"
          onsubmit="return validateEventForm();">

        <div class="form-group">
            <label>Category:</label>
            <select name="categoryId" required>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Title:</label>
            <input type="text" name="title" required maxlength="120">
        </div>

        <div class="form-group">
            <label>Description:</label>
            <input type="text" name="description" maxlength="255">
        </div>

        <div class="form-group">
            <label>Venue:</label>
            <input type="text" name="venue" required>
        </div>

        <div class="form-group">
            <label>Date / Time:</label>
            <input type="datetime-local" name="eventDate" required>
        </div>

        <div class="form-group">
            <label>Price (LKR):</label>
            <input type="number" name="price" step="0.01" min="0" value="0">
        </div>

        <div class="form-row">
            <div>
                <label>Total Seats:</label>
                <input type="number" name="seatsTotal" min="0" value="100">
            </div>
            <div>
                <label>Available Seats:</label>
                <input type="number" name="seatsAvailable" min="0" value="100">
            </div>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <select name="status">
                <option value="ACTIVE" selected>ACTIVE</option>
                <option value="CLOSED">CLOSED</option>
            </select>
        </div>

        <div class="form-buttons">
            <button type="submit">Save Event</button>
            <a class="btn-cancel" href="${pageContext.request.contextPath}/admin/events">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>

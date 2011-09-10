<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<form method="post">
		<select name="selectedScript">
			<c:forEach items="${scripts}" var="script" varStatus="status">
				<c:choose>
					<c:when test="${selectedScript==status.index}">
						<option value="${status.index}" selected="selected">${script.name}</option>
					</c:when>
					<c:otherwise>
						<option value="${status.index}">${script.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<input type="submit">
	</form>
</body>
</html>
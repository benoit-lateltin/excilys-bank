<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty messages}">
<script>
<c:forEach items="${messages}" var="message">
	message('<spring:message code="${message.key}" arguments="${message.args}" />');
</c:forEach>
</script>
</c:if>
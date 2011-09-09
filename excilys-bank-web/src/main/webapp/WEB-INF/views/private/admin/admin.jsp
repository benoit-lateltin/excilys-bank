<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<h1>
	<spring:message code="admin.welcome" />
</h1>
<br />
<spring:message code="admin.databaseContent" />
<ul>
	<li><spring:message code="admin.userCount" arguments="${userCount}" /></li>
	<li><spring:message code="admin.accountCount" arguments="${accountCount}" /></li>
	<li> <spring:message code="admin.operationCount" arguments="${operationCount}" /></li>
</ul>

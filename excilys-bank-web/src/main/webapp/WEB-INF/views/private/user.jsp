<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="login">
	<a href="${bk:ctx()}/logout" class="button blue"><spring:message code="common.logout" /></a>
</div>
<div class="login">
	<security:authentication property="principal.user.firstName" />
	<security:authentication property="principal.user.lastName" />
</div>
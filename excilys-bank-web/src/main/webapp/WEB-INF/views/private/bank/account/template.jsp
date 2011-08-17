<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<h1>
	<a href="${bk:ctx()}/private/bank/accounts.html">
		<spring:message code="accounts.welcome" />
	</a>
	&gt;
	${account.type.id} ${account.number}
</h1>

<div class="container">
	<tiles:insertAttribute name="account.details"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.menu"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.tabs"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.body"/>
</div>
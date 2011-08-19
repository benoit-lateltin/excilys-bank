<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="sw">
	<div class="sw_icon icon users"></div>
	<div class="sw_header"><spring:message code="login.title" /></div>
	<div class="sw_content">
		<form method="post" id="loginForm" action="${bk:ctx()}/login">
			<fieldset>
				<legend><spring:message code="login.legend" /></legend>
				<ol>
					<li>
						<label for="username"><spring:message code="login.username" /></label>
						<input type="text" id="username" name="username" class="text"/>
					</li>
					<li>
						<label for="password"><spring:message code="login.password" /></label>
						<input type="password" id="password" name="password" class="text"/>
					</li>
					<li class="center">
						<input type="submit" value="<spring:message code="login.submit" />" class="button blue"/>
					</li>
				</ol>
			</fieldset>
		</form>
		<div class="cb"></div>
	</div>
	<div class="sw_footer"></div>
</div>
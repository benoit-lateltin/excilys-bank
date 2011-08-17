<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="sw">
	<div class="sw_icon icon users"></div>
	<div class="sw_header"><spring:message code="login.welcome" /></div>
	<div class="sw_content">
		<form method="post" id="loginForm" action="<c:url value='/j_spring_security_check'/>">
			<fieldset>
				<legend>Please log in</legend>
				<ol>
					<li>
						<label for="j_username">Identifiant</label>
						<input type="text" id="j_username" name="j_username" class="text"/>
					</li>
					<li>
						<label for="j_password">Mot de passe</label>
						<input type="password" id="j_password" name="j_password" class="text"/>
					</li>
					<li class="center">
						<input type="submit" value="Connexion" class="submit"/>
					</li>
				</ol>
			</fieldset>
		</form>
		<div class="cb"></div>
	</div>
	<div class="sw_footer"></div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login">
	<a href="<c:url value="/j_spring_security_logout"/>">Déconnexion</a>
</div>
<div class="login">
	${userName}
</div>
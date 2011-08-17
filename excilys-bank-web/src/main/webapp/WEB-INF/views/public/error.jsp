<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/stylesheets/style.css" />" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${title}</title>
	</head>

	<body>

		<!-- Main Container -->
		<div id="container">

			<!-- Small window-->
			<div class="sw">
<!--			-->
<!--				<div class="sw_icon">-->
<!--					<img src="<c:url value="/resources/images/users.png" />" alt="user" />-->
<!--				</div>-->

				<div class="sw_header">
					${title}
				</div>

				<div class="sw_content">
					<div class="message">
						${message}
					</div>
				</div>

				<div class="sw_footer" />

			</div>

		<!-- End of container -->
		</div>

	</body>
</html>
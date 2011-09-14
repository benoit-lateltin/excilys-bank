<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<tiles:insertAttribute name="common.head"/>
	</head>
	<body>
		<div id="container">
			<tiles:insertAttribute name="common.body" />
		</div>
		<div class="icon logo"></div>
		<tiles:insertAttribute name="common.scripts"/>
		<tiles:insertAttribute name="common.messages" />
		<tiles:insertAttribute name="common.scripts-specific" ignore="true" />
	</body>
</html>
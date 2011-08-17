<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags/jsp_2x"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="bw">
	<div class="bw_header">
		<div class="menu">
		A VIRER
		</div>
		<div class="userInfo">
			<tiles:insertAttribute name="private.user" />
		</div>
	</div>
	<div class="bw_content">
		<tiles:insertAttribute name="private.body" />
	</div>
	<div class="bw_footer"></div>
</div>

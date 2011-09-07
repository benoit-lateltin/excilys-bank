<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="bw">
	<div class="bw_header">
		<div class="menu">
			<security:authorize ifAnyGranted="ROLE_USER">
				<div class="tab left ${bk:if(page.hasAncestor('ACCOUNT'), 'active', '')}">
					<div class="title">
						<a href="${bk:ctx()}/private/bank/accounts.html">
							<span class="icon bank left"></span>
							<span class="iconLabel left">Accounts</span>
						</a>
					</div>
				</div>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<div class="tab left ${bk:if(page.hasAncestor('ADMIN'), 'active', '')}">
					<div class="title">
						<a href="${bk:ctx()}/private/admin/admin.html">
							<span class="icon admin left"></span>
							<span class="iconLabel left">Admin</span>
						</a>
					</div>
				</div>
			</security:authorize>
			<div class="after left"></div>
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

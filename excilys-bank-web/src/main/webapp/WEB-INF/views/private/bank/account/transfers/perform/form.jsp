<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="block">
	<div class="body">
		<form:form commandName="transferCommand" action="perform.html">
			<fieldset>
				<legend><spring:message code="transfers.form.title" /></legend>
				<ol>
					<li>
						<label for="debitedAccountNumber"><spring:message code="transfers.form.debited" /></label>
						<form:select path="debitedAccountNumber">
							<c:forEach items="${debitableAccounts}" var="account">
								<form:option value="${account.number}">${account.type.id} ${account.number} (${account.balance})</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="debitedAccountNumber" />
					</li>
					<li>
						<label for="creditedAccountNumber"><spring:message code="transfers.form.credited" /></label>
						<form:select path="creditedAccountNumber">
							<c:forEach items="${creditableAccounts}" var="account">
								<form:option value="${account.number}">${account.type.id} ${account.number} (${account.balance})</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="creditedAccountNumber" />
					</li>
					<li>
						<label for="amount"><spring:message code="transfers.form.amount" /></label>
						<form:input path="amount" />
						<form:errors path="amount" />
					</li>
				</ol>
				<ol>
					<li class="center">
						<input type="submit" class="submit" value="Perform" />
					</li>
				</ol>
			</fieldset>
		</form:form>
		<div class="cb"></div>
	</div>
</div>


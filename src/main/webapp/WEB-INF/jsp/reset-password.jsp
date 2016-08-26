<%@include file="includes/header.jsp"%>

<div class="panel panel-default">

	<div>
		<h3 class="panel-title">Reset password</h3>
	</div>
	
	<div class="panel-body">
		
		<form:form modelAttribute="resetPasswordForm" role="form">
		
			<form:errors />
			
			<div class="form-group">
				<form:label path="password">Type new password</form:label>
				<form:input path="password" type="password" class="form-control" placeholder="Enter password"/>
				<form:errors cssClass="error" path="password" />
			</div>
			
			<div class="form-group">
				<form:label path="retypePassword">Type new password</form:label>
				<form:input path="retypePassword" type="password" class="form-control" placeholder="Enter password"/>
				<form:errors cssClass="error" path="retypePassword" />
			</div>
			
			<button type="submit" class="btn btn-default">Save</button>
		
		</form:form>
			
	</div>

</div>

<%@include file="includes/footer.jsp" %>
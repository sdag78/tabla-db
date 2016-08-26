<%@include file="includes/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Create new composition</h3>
	</div>

	<div class="panel-body">
	
		<div id="bolTableDiv" >
	    	<br>
	    	<br>
	    </div>
		
		<br>
		
		<form:form modelAttribute="createCompositionForm" role="form">
			
			<form:errors />
				
			<div class="form-group">
				<form:label path="name">name</form:label>
				<form:input path="name" class="form-control" placeholder="name"/>
				<form:errors cssClass="error" path="name" />
			</div>
			
			<div class="form-group">
				<form:label path="bols">Bols</form:label>
				<form:textarea path="bols" name="bols" id="bolTextArea" rows="4" cols="100" onkeyup="cc_updateBolTable(${createCompositionForm.numberOfColumns})"/>
				<form:errors cssClass="error" path="bols" />
				<p class="help-block">Enter bols, using a comma or newline to separate each beat.</p>
			</div>

			<br>
			
			<div class="form-group">
				<form:label path="comments">comments</form:label>
				<form:input path="comments" class="form-control" placeholder="comments"/>
				<form:errors cssClass="error" path="comments" />
			</div>
			
			<div class="form-group">
				<form:label path="ordering">ordering</form:label>
				<form:input path="ordering" class="form-control" placeholder="ordering"/>
				<form:errors cssClass="error" path="ordering" />
			</div>
			
			<button type="submit" class="btn btn-default">Save</button>

		</form:form>
					
	</div>

</div>

<script src="/public/lib/javascript/cc_getHtmlBolsTable.js"></script>

<%@include file="includes/footer.jsp" %>
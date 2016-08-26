<%@include file="includes/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Create new piece</h3>
	</div>

	<div class="panel-body">
	
		<form:form modelAttribute="createPieceForm" role="form">
			
			<!-- will define a form for entering: name, number of columns, description -->
				
			<form:errors />
		
			<div class="form-group">
				<form:label path="name">Piece name</form:label>
				<form:input path="name" class="form-control" placeholder="Piece name"/>
				<form:errors cssClass="error" path="name" />
				<p class="help-block">Enter a unique email address. It will also be your login id</p>
			</div>
			
			<div class="form-group">
				<form:label path="firstFewBols">First few bols</form:label>
				<form:input path="firstFewBols" class="form-control" placeholder="firstFewBols" value="${editPieceForm.firstFewBols}"/>
				<form:errors cssClass="error" path="firstFewBols" />
			</div>
			
			<div class="form-group">
				<form:label path="numberOfBeats">Number of columns</form:label>
				<form:input path="numberOfBeats" class="form-control" placeholder="Number of columns"/>
				<form:errors cssClass="error" path="numberOfBeats" />
				<p class="help-block">Enter your display name</p>
			</div>
			
			<div class="form-group">
				<form:label path="paltaTableNumber">Palta table number</form:label>
				<form:input path="paltaTableNumber" class="form-control" placeholder="paltaTableNumber" value="${editPieceForm.paltaTableNumber}"/>
				<form:errors cssClass="error" path="paltaTableNumber" />
			</div>
			
			<div class="form-group">
				<form:label path="description">Description</form:label>
				<form:input path="description" class="form-control" placeholder="description"/>
				<form:errors cssClass="error" path="description" />
			</div>
			
			<div class="form-group">
				<form:label path="source">source</form:label>
				<form:input path="source" class="form-control" placeholder="source" value="${editPieceForm.source}"/>
				<form:errors cssClass="error" path="source" />
			</div>
			
			<button type="submit" class="btn btn-default">Save</button>

		</form:form>
					
	</div>

</div>

<%@include file="includes/footer.jsp" %>
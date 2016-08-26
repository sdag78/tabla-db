<%@include file="includes/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Edit piece</h3>
	</div>

	<div class="panel-body">
	
		<form:form modelAttribute="editPieceForm" role="form">
			
			<!-- will define a form for entering: bols, comments -->
			<form:errors />
			
			<div class="form-group">
				<form:label path="name">name</form:label>
				<form:input path="name" class="form-control" placeholder="name" value="${editPieceForm.name}"/>
				<form:errors cssClass="error" path="name" />
			</div>
			
			
			<div class="form-group">
				<form:label path="firstFewBols">First few bols</form:label>
				<form:input path="firstFewBols" class="form-control" placeholder="firstFewBols" value="${editPieceForm.firstFewBols}"/>
				<form:errors cssClass="error" path="firstFewBols" />
			</div>
			
			<div class="form-group">
				<form:label path="description">description</form:label>
				<form:input path="description" class="form-control" placeholder="description" value="${editPieceForm.description}"/>
				<form:errors cssClass="error" path="description" />
			</div>
			
			<div class="form-group">
				<form:label path="paltaTableNumber">Palta table number</form:label>
				<form:input path="paltaTableNumber" class="form-control" placeholder="paltaTableNumber" value="${editPieceForm.paltaTableNumber}"/>
				<form:errors cssClass="error" path="paltaTableNumber" />
			</div>
			
			<div class="form-group">
				<form:label path="numberOfBeats">number of beats</form:label>
				<form:input path="numberOfBeats" class="form-control" placeholder="numberOfBeats" value="${editPieceForm.numberOfBeats}"/>
				<form:errors cssClass="error" path="numberOfBeats" />
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
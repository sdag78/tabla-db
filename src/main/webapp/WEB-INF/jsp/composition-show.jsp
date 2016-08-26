<%@include file="includes/header.jsp" %>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Piece: ${piece.name}</h3>
	</div>
	
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Number of beats</th>
			<th>Description</th>
		</tr>
			<tr>
				<td><c:out value="${piece.name}" /></td>
				<td><c:out value="${piece.numberOfBeats}" /></td>
				<td><c:out value="${piece.description}" /></td>
				<!--  TODO: number of compositions -->
			</tr>
	</table>
	
</div>

	
<div class="panel panel-primary">

	<!--  
		TODO: 
		- list the compositions here, as a border outlined table. 
		- Implement the csv to html transform in java, by replacing the commas with cell delimiters 
	-->
	
	<table class="table">
		<tr>
			<th>notes</th>
			<th>compositionPid</th>
			<th>piecePid</th>
			<th>ordering</th>
		</tr>
			<tr>
				<td><c:out value="${composition.notes}" /></td>
				<td><c:out value="${composition.compositionPid}" /></td>
				<td><c:out value="${composition.piecePid}" /></td>
				<td><c:out value="${composition.ordering}" /></td>
			</tr>
	</table>
	
	<table class="table">
		<tr>
			<td><c:out value="${composition.bols}" /></td>
		</tr>
	</table>
	
	
</div>

<%@include file="includes/footer.jsp" %>
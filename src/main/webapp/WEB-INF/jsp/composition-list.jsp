<%@include file="includes/header.jsp" %>
	
	<c:forEach var="composition" items="${compositions}" >

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">NEW DISPLAY: Piece: ${piece.name}, ${piece.piecePid}</h3>
			</div>
	
		
			<table class="table">
				<tr>
					<th>compositionPid</th>
					<th>piecePid</th>
					<th>name</th>
					<th>notes</th>
				</tr>
			
				<tr>
					<td><c:out value="${composition.compositionPid}" /></td>
					<td><c:out value="${composition.piecePid}" /></td>
					<td><c:out value="${composition.name}" /></td>
					<td><c:out value="${composition.notes}" /></td>
				</tr>
				
			</table>
			
			
			<table class="table" id="composition${composition.compositionPid}" onload="setHtmlBolsTable(${composition.bols}, ${piece.numberOfBeats}, 'composition'${composition.compositionPid})">
			</table>

			
		</div>
		
	</c:forEach>
	
	
	

	<div class="panel-footer">
		<a class="btn btn-link" href="/createPiece">Create new Composition</a>
	</div>

<%@include file="includes/footer.jsp" %>
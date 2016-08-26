<%@include file="includes/header.jsp" %>
	
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Pieces</h3>
	</div>
	
	<table class="table">
		<tr>
			<th> </th>
			
			<th>Name</th>
			<th>Kaida line</th>
			<th>Description</th>
<!-- 			<th>Number of compositions</th> -->
			<th>Palta table number</th>
			<th>Number of columns</th>
			<th>source</th>
			
		</tr>
		<c:forEach var="piece" items="${userPieces}" >
			<tr>
			<td>
				<div class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="/pieces/${piece.piecePid}/edit">Edit Piece properties</a></li>
		            <li><a href="/pieces/${piece.piecePid}/">View Piece</a></li>
		          </ul>
		        </div>
			    </td>    
				
				<td><a href="/pieces/${piece.piecePid}"><c:out value="${piece.name}" /></a></td>
<%-- 				<td><c:out value="${piece.numberOfCompositions}" /></td> --%>
				<td><c:out value="${piece.firstFewBols}" /></td>
				<td><c:out value="${piece.description}" /></td>
				<td><c:out value="${piece.paltaTableNumber}" /></td>
				<td><c:out value="${piece.numberOfBeats}" /></td>
				<td><c:out value="${piece.source}" /></td>
				
			</tr>
		</c:forEach>
	</table>
	
	
	<div class="panel-footer">
		<a class="btn btn-link" href="/createPiece">Create new Piece</a>
	</div>
	
</div>

<%@include file="includes/footer.jsp" %>
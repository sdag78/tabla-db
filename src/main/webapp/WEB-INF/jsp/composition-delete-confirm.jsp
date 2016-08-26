<%@include file="includes/header.jsp" %>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Confirm deletion of Composition ${composition.compositionPid} from Piece: ${piece.name}</h3>
	</div>
	
	
	<!--  
		TODO: 
		- list the compositions here, as a border outlined table. 
		- Implement the csv to html transform in java, by replacing the commas with cell delimiters 
	-->
	
	<table class="table">
		<tr>
			<th>notes</th>
			<th>order_number</th>
			<th>composition_id</th>
			<th>piece_id</th>
		</tr>
			<tr>
				<td><c:out value="${composition.notes}" /></td>
				<td><c:out value="${composition.compositionPid}" /></td>
				<td><c:out value="${composition.piecePid}" /></td>
			</tr>
	</table>
	
	<jsp:useBean id="bolsToHtmlTable" class="com.stevengiblin.spring.taleemdb.util.BolsToHtmlTable"/>
	<jsp:setProperty name="bolsToHtmlTable" property="csvBols" value="${composition.bols}"/>
	<jsp:setProperty name="bolsToHtmlTable" property="numberOfBeats" value="${piece.numberOfBeats}"/>
	<jsp:getProperty name="bolsToHtmlTable" property="htmlTable" />
				
	
	<div class="panel-footer">
		<a class="btn btn-link" href="/pieces/${piecePid}/compositions/${compositionPid}/deleteConfirm">Confirm delete</a>
		<a class="btn btn-link" href="/pieces/${piecePid}/">Cancel</a>
	</div>
	
</div>

<%@include file="includes/footer.jsp" %>
<%@include file="includes/header.jsp" %>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<div class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span>${piece.name}</a>
	          <ul class="dropdown-menu">
	            <li><a href="/pieces/${piece.piecePid}/edit">Edit piece properties</a></li>
	            <!-- <li><a href="/pieces/${piece.piecePid}/delete">Todo: delete</a></li> -->
	          </ul>
	        </div>
		</div>
		
		<div class="panel-body">
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Number of beats</th>
					<th>Description</th>
					<th>Palta table number</th>
					<th>source</th>
				</tr>
					<tr>
						<td><c:out value="${piece.name}" /></td>
						<td><c:out value="${piece.numberOfBeats}" /></td>
						<td><c:out value="${piece.description}" /></td>
						<td><c:out value="${piece.paltaTableNumber}" /></td>
						<td><c:out value="${piece.source}" /></td>
						<!--  TODO: number of compositions -->
					</tr>
			</table>
		</div>
		
	</div>
	
	
	<c:if test="${not empty compositions}">
			<c:forEach var="composition" items="${compositions}" >
			
			<div class="panel panel-default">
				<div class="panel-heading">
					
					<div class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span>${composition.name}</a>
			          <ul class="dropdown-menu">
			            <li><a href="/pieces/${piece.piecePid}/compositions/${composition.compositionPid}/edit">Edit composition</a></li>
			            <li><a href="/pieces/${piece.piecePid}/compositions/${composition.compositionPid}/delete">Delete composition</a></li>
<%-- 			            <li><a href="/pieces/${piece.piecePid}/compositions/${composition.compositionPid}/duplicate">Duplicate</a></li> --%>
			          </ul>
			        </div>
					
				</div>
				
				
				<jsp:useBean id="bolsToHtmlTable" class="com.stevengiblin.spring.taleemdb.util.BolsToHtmlTable"/>
				<jsp:setProperty name="bolsToHtmlTable" property="csvBols" value="${composition.bols}"/>
				<jsp:setProperty name="bolsToHtmlTable" property="numberOfBeats" value="${piece.numberOfBeats}"/>
				<jsp:getProperty name="bolsToHtmlTable" property="htmlTable" />
				
				
				<div class="panel-footer">
					<c:if test="${not empty composition.notes}">
						<c:out value="Notes: ${composition.notes}" />
						
					</c:if>
				</div>
			</div>
		</c:forEach>
	</c:if>
	
	<a class="btn btn-link" href="/pieces/${piece.piecePid}/createComposition">Create composition</a>
		
<%@include file="includes/footer.jsp" %>
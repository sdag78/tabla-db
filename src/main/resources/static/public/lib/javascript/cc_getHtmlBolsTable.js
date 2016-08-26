// todo: pass columns as param
function cc_updateBolTable(columns) {
    var csvBols = document.getElementById('bolTextArea').value;
    cc_setHtmlBolsTable(csvBols, columns, 'bolTableDiv');
  }
  
function cc_setHtmlBolsTable(csvBols, columns, elementId) {
  	document.getElementById(elementId).innerHTML=cc_getHtmlBolsTable(csvBols, columns);
}

function cc_getHtmlBolsTable(csvBols, columns) {

  // if regex detects bad characters, then bail out
	var patt = /[^a-zA-Z0-9, ()\[\]\?@!\*\=\+\n\f\r\t]/; //[a-zA-Z0-9,. ()=\\[\\]\\:$#£!@\\*]*
	
	if (patt.test(csvBols)) {
		return "<p>input validation failed</p>";
 	}
	
  var bols = csvBols.split(new RegExp(/[\n,]/));
  var row=bols.length / columns;
  var tempTable = [];
  tempTable.push('<table id="idBolTable" class="classBolTable">');

  tempTable.push("<tr>");
  for (i=0;i<columns;i++){
    tempTable.push("<th>"+(i+1)+"</th>");
  }
  tempTable.push("</tr>");

  var count = 0;
  for (r=0;r<row;r++){
    tempTable.push("<tr>");
    for (j=0;j<columns;j++){
      val="";
      if (count<bols.length) {
        val=bols[count++];
      }
      tempTable.push("<td>"+val+"</td>");
    }
    tempTable.push("</tr>");
  }


  tempTable.push("</table>");
  return tempTable.join("");
}
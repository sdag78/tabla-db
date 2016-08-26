//function v1_hello() {
//  var csvBols = document.getElementById('bolTextArea').value;
//  document.getElementById('tableDiv').innerHTML=v1_getHtmlBolsTable(csvBols, 8);
//}
//
//function v1_setHtmlBolsTable(csvBols, columns, elementId) {
//  	document.getElementById(elementId).innerHTML=v1_getHtmlBolsTable(csvBols, 8);
//}
//
//function v1_getHtmlBolsTable(csvBols, columns) {
//
//  var bols = csvBols.split(new RegExp(/[\n,]/));
//  var row=bols.length / columns;
//  var tempTable = [];
//  tempTable.push("<table>");
//
//  tempTable.push("<tr>");
//  for (i=0;i<columns;i++){
//    tempTable.push("<th>"+(i+1)+"</th>");
//  }
//  tempTable.push("</tr>");
//
//  var count = 0;
//  for (r=0;r<row;r++){
//    tempTable.push("<tr>");
//    for (j=0;j<columns;j++){
//      val="";
//      if (count<bols.length) {
//        val=bols[count++];
//      }
//      tempTable.push("<td>"+val+"</td>");
//    }
//    tempTable.push("</tr>");
//  }
//
//
//  tempTable.push("</table>");
//  return tempTable.join("");
//}
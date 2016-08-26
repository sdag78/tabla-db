package com.stevengiblin.spring.taleemdb.util;

public class BolsToHtmlTable {
	
	private String csvBols;
	private int numberOfBeats;

	public void setCsvBols(String csvBols) {
		this.csvBols = csvBols;
	}

	public void setNumberOfBeats(int numberOfBeats) {
		this.numberOfBeats = numberOfBeats;
	}
	
	public String getHtmlTable() {
		String [] bols = csvBols.split("[\n,]");
		
		if (numberOfBeats==0) numberOfBeats=8; // render as default 8 beats if user didn't set a value

		double d_length = (double)bols.length;
		double d_beats = (double)numberOfBeats;
		
		int rows=(int) Math.ceil(d_length / d_beats);
		
//		System.out.println("**********************************************************");
//		System.out.println("In BolsToHtmlTable.getHtmlTable()");
//		System.out.println("bols.length: " + bols.length);
//		System.out.println("numberOfBeats: " + numberOfBeats);
//		System.out.println("rows: " + rows);
//		System.out.println("**********************************************************");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='bolTable' border='1' width='100%'>");

		sb.append("<tr>");
		for (int i=0;i<numberOfBeats;i++){
			sb.append("<th>"+(i+1)+"</th>");
		}
		sb.append("</tr>");

		int count = 0;
		for (int r=0;r<rows;r++){
			sb.append("<tr>");
			for (int j=0;j<numberOfBeats;j++){
				String val="";
				if (count<bols.length) {
					val=bols[count++].trim();
				}
				sb.append("<td>"+val+"</td>");
			}
			sb.append("</tr>");
		}

		sb.append("</table>");
		return sb.toString();
	}

	public static void main(String args[]) {
		String bols = "Dha, Dhin, Dhin, Dha, Dha, Dhin, Dhin, Dha, Dha, Tin, Tin, Ta, Dha, Dhin, Dhin, Dha";
		BolsToHtmlTable obj = new BolsToHtmlTable();
		obj.setCsvBols(bols);
		obj.setNumberOfBeats(8);
		System.out.println(obj.getHtmlTable());
	}
}
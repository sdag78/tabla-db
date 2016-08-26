package com.stevengiblin.spring.taleemdb.dto;

import com.stevengiblin.spring.taleemdb.entities.Composition;
import com.stevengiblin.spring.taleemdb.validators.NoScript;

public class EditCompositionForm {
	
	@NoScript
	private String name;
	
	@NoScript
	private String bols;
	
	@NoScript
	private String comments;

	private float ordering;
	private long pieceId;
	private long compositionId;
	private long compositionPid;
	private int numberOfColumns;
	
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	public void setOrdering(float ordering) {
		this.ordering = ordering;
	}
	public EditCompositionForm() { 
		
	}
	public EditCompositionForm(Composition composition, int numberOfColumns) {
		bols = composition.getBols();
		comments = composition.getNotes();
		pieceId = composition.getPieceId();
		compositionId = composition.getCompositionId();
		compositionPid = composition.getCompositionPid();
		name = composition.getName();
		this.numberOfColumns = numberOfColumns;
	}
	public long getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(long compositionId) {
		this.compositionId = compositionId;
	}
	public long getCompositionPid() {
		return compositionPid;
	}
	public void setCompositionPid(long compositionPid) {
		this.compositionPid = compositionPid;
	}
	public long getPieceId() {
		return pieceId;
	}
	public void setPieceId(long pieceId) {
		this.pieceId = pieceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBols() {
		return bols;
	}
	public void setBols(String bols) {
		this.bols = bols;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String notes) {
		this.comments = notes;
	}
	
	@Override
	public String toString() {
		return "EditCompositionForm [name=" + name + ", bols=" + bols + ", comments=" + comments + ", pieceId="
				+ pieceId + ", compositionId=" + compositionId + ", compositionPid=" + compositionPid + ", ordering="
				+ ordering + "]";
	}
	public float getOrdering() {
		return ordering;
	}
	
}

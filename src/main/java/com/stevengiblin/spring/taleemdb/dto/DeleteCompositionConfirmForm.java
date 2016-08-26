package com.stevengiblin.spring.taleemdb.dto;

import javax.validation.constraints.NotNull;

import com.stevengiblin.spring.taleemdb.entities.Composition;

public class DeleteCompositionConfirmForm {
	
	private String name;
	
	@NotNull
	private String bols;
	private String comments;
	private long pieceId;
	private long compositionId;
	
	public DeleteCompositionConfirmForm() { 
	}
	public DeleteCompositionConfirmForm(Composition composition) {
		bols = composition.getBols();
		comments = composition.getNotes();
		pieceId = composition.getPieceId();
		compositionId = composition.getCompositionId();
	}
	public long getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(long compositionId) {
		this.compositionId = compositionId;
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
		return "CreateCompositionForm [name=" + name + ", bols=" + bols + ", notes=" + comments + ", pieceId=" + pieceId + "]";
	}

}

package com.stevengiblin.spring.taleemdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.stevengiblin.spring.taleemdb.entities.Piece;
import com.stevengiblin.spring.taleemdb.validators.NoScript;

public class EditPieceForm {
	
	@NoScript
	@NotNull
	@Size(min=1, max=Piece.MAX_TITLE_LENGTH, message="{pieceNameError}")
	private String name;
	
	@NotNull
	private int numberOfBeats;
	
	@NoScript
	@NotNull
	@Size(min=0, max=Piece.MAX_DESCRIPTION_LENGTH, message="{pieceMaxDescriptionLengthError}")
	private String description;

	@NotNull
	private long pieceId;
	
	@NotNull
	private long piecePid;
	
	@NoScript
	@NotNull
	private String paltaTableNumber;
	
	@NoScript
	@NotNull
	private String firstFewBols;

	@NoScript
	private String source;
	
	public EditPieceForm() {
		
	}
	public EditPieceForm(Piece piece) {
		this.pieceId = piece.getPieceId();
		this.pieceId = piece.getPiecePid();
		this.name = piece.getName();
		this.numberOfBeats = piece.getNumberOfBeats();
		this.description = piece.getDescription();
		this.paltaTableNumber = piece.getPaltaTableNumber();
		this.firstFewBols = piece.getFirstFewBols();
		this.source = piece.getSource();
	}

	@Override
	public String toString() {
		return "EditPieceForm [pieceId =" + pieceId + ", pieceName=" + name + ", numberOfBeats=" + numberOfBeats + ", description="
				+ description + "]";
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFirstFewBols() {
		return firstFewBols;
	}
	public void setFirstFewBols(String firstFewBols) {
		this.firstFewBols = firstFewBols;
	}
	public String getName() {
		return name;
	}
	public void setName(String pieceName) {
		this.name = pieceName;
	}
	public int getNumberOfBeats() {
		return numberOfBeats;
	}
	public void setNumberOfBeats(int numberOfBeats) {
		this.numberOfBeats = numberOfBeats;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPieceId(long pieceId) {
		this.pieceId = pieceId;
	}
	public long getPieceId() {
		return pieceId;
	}
	public void setPiecePid(long piecePid) {
		this.piecePid = piecePid;
	}
	public long getPiecePid() {
		return piecePid;
	}
	public String getPaltaTableNumber() {
		return paltaTableNumber;
	}
	public void setPaltaTableNumber(String paltaTableNumber) {
		this.paltaTableNumber = paltaTableNumber;
	}
	
}

package com.stevengiblin.spring.taleemdb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="Pieces", indexes = {
		@Index(columnList="pieceId", unique=true)
})

public class Piece {
	
	public static final int NAME_MAX = 50;
	public static final int MAX_TITLE_LENGTH = 128;
	public static final int MAX_NUM_BEATS = 128;
	public static final int MAX_DESCRIPTION_LENGTH = 256;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pieceId;
	
	@Column
	private long piecePid;
	
	@Column(nullable = false, length = NAME_MAX)
	private String name;
	
	@Column(nullable = false)
	private int numberOfBeats;
	
	@Column
	private String description;

	@Column(nullable = false)
	private long userId;
	
	@Column
	private String paltaTableNumber;
	
	@Column
	private String source;
	
	@Column
	private int ordering = -1;
	
	@Column(nullable = false)
	private String firstFewBols;
	
	private int origCounter;
	
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
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int order) {
		this.ordering = order;
	}
	public String getPaltaTableNumber() {
		return paltaTableNumber;
	}
	public void setPaltaTableNumber(String origPaltaTableNumber) {
		this.paltaTableNumber = origPaltaTableNumber;
	}
	public int getOrigCounter() {
		return origCounter;
	}
	public void setOrigCounter(int origCounter) {
		this.origCounter = origCounter;
	}
	public long getPieceId() {
		return pieceId;
	}
	public void setPieceId(long pieceId) {
		this.pieceId = pieceId;
	}
	public long getPiecePid() {
		return piecePid;
	}
	public void setPiecePid(long piecePid) {
		this.piecePid = piecePid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfBeats() {
		return numberOfBeats;
	}
	public void setNumberOfBeats(int i) {
		this.numberOfBeats = i;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
	}

}

package com.stevengiblin.spring.taleemdb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="Compositions", indexes = {
		@Index(columnList="compositionId", unique=true)
})

public class Composition {
	
	public static final int NAME_MAX = 50;
	public static final int MAX_BOLS_LENGTH = 1024;
	public static final int MAX_NOTES_LENGTH = 512;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long compositionId;
	
	@Column
	private long compositionPid;
	
	@Column
	private long userId;
	
	@Column(nullable = false)
	private long pieceId;
	
	@Column(length = NAME_MAX)
	private String name;
	
	@Column(nullable = false, length = MAX_BOLS_LENGTH)
	private String bols;
	
	@Column(nullable = false, length = MAX_NOTES_LENGTH)
	private String notes;
	
	@Column(nullable = false)
	private float ordering;
	
	private int origCounter;
	
	@Override
	public String toString() {
		return "Composition [compositionId=" + compositionId + ", pieceId=" + pieceId + ", name=" + name + ", bols="
				+ bols + ", notes=" + notes + ", origCounter=" + origCounter
				+ "]";
	}
	
	public float getOrdering() {
		return ordering;
	}
	public void setOrdering(float order) {
		this.ordering = order;
	}
	public int getOrigCounter(int counter) {
		return this.origCounter;
	}
	public void setOrigCounter(int counter) {
		this.origCounter = counter;
	}
	public String getBols() {
		return bols;
	}
	public void setBols(String bols) {
		this.bols = bols;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
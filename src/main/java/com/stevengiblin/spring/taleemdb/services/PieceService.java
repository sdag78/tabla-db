package com.stevengiblin.spring.taleemdb.services;

import java.util.List;

import com.stevengiblin.spring.taleemdb.dto.CreatePieceForm;
import com.stevengiblin.spring.taleemdb.dto.EditPieceForm;
import com.stevengiblin.spring.taleemdb.entities.Piece;

public interface PieceService {

	public long createPiece(CreatePieceForm createPieceForm);
	public List<Piece> getPieces();
	public Piece findByPiecePidAndUserId(String piecePid, long userId);
	public Piece findByPiecePidAndUserId(int piecePid, long userId);
	public Piece editPiece(String piecePid, EditPieceForm editPieceForm);
	Piece findByPieceId(long pieceId);
	public long getPieceIdFromPiecePid(String piecePid);

}

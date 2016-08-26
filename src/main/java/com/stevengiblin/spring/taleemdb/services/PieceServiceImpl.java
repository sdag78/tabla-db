package com.stevengiblin.spring.taleemdb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stevengiblin.spring.taleemdb.dto.CreatePieceForm;
import com.stevengiblin.spring.taleemdb.dto.EditPieceForm;
import com.stevengiblin.spring.taleemdb.entities.Piece;
import com.stevengiblin.spring.taleemdb.repositories.PieceRepository;
import com.stevengiblin.spring.taleemdb.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PieceServiceImpl implements PieceService {

	private static final Logger logger = LoggerFactory.getLogger(PieceServiceImpl.class);
	
	private PieceRepository pieceRepository;
	
	@Autowired
	public PieceServiceImpl(PieceRepository pieceRepository) {
		this.pieceRepository = pieceRepository;
	}

	public long createPiece(CreatePieceForm createPieceForm) {
		final Piece piece = new Piece();
		piece.setName(createPieceForm.getName());
		piece.setNumberOfBeats(createPieceForm.getNumberOfBeats());
		piece.setDescription(createPieceForm.getDescription());
		piece.setUserId(MyUtil.getSessionUser().getId());
		piece.setFirstFewBols(createPieceForm.getFirstFewBols());
		piece.setPaltaTableNumber(createPieceForm.getPaltaTableNumber());
		piece.setSource(createPieceForm.getSource());
		
		long piecePid = getNewPiecePID();
		piece.setPiecePid(piecePid);
		
		pieceRepository.save(piece);
		return piecePid;
	}

	private long getNewPiecePID() {
		long max = pieceRepository.getMaxPiecePidByUserId(MyUtil.getSessionUser().getId());
		return max + 1;
	}

	@Override
	public List<Piece> getPieces() {
		return pieceRepository.findByUserId(MyUtil.getSessionUser().getId());
	}

	@Override
	public Piece findByPiecePidAndUserId(String pieceId, long userId) {
		return findByPiecePidAndUserId(Integer.parseInt(pieceId), userId);
	}
	
	@Override
	public Piece findByPieceId(long pieceId) {
		return findByPieceId((int)pieceId);
	}

	@Override
	public Piece findByPiecePidAndUserId(int pieceId, long userId) {
		return pieceRepository.findByPiecePidAndUserId(pieceId, userId);
	}

	@Override
	public Piece editPiece(String piecePid, EditPieceForm editPieceForm) {
		final Piece piece = findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		
		piece.setName(editPieceForm.getName());
		piece.setDescription(editPieceForm.getDescription());
		piece.setNumberOfBeats(editPieceForm.getNumberOfBeats());
		piece.setPaltaTableNumber(editPieceForm.getPaltaTableNumber());
		piece.setFirstFewBols(editPieceForm.getFirstFewBols());
		piece.setSource(editPieceForm.getSource());
		
		pieceRepository.save(piece);
		return piece;
	}

	@Override
	public long getPieceIdFromPiecePid(String piecePid) {
		Piece piece = findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		return piece.getPieceId();
	}
	
}

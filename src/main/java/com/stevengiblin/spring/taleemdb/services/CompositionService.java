package com.stevengiblin.spring.taleemdb.services;

import java.util.List;

import com.stevengiblin.spring.taleemdb.dto.CreateCompositionForm;
import com.stevengiblin.spring.taleemdb.dto.EditCompositionForm;
import com.stevengiblin.spring.taleemdb.entities.Composition;

public interface CompositionService {

	List<Composition> findByPieceId(long pieceId);
	List<Composition> findByPieceId(String pieceId);

	Composition createComposition(CreateCompositionForm createCompositionForm);

	List<Composition> getCompositions(long pieceId);
	List<Composition> getCompositions(String pieceId);

	Composition findByPieceIdAndCompositionPid(long pieceId, String compositionPid);
	Composition findByPieceIdAndCompositionPid(long pieceId, long compositionPid);
	
	Composition findByCompositionId(String compositionId);
	Composition findByCompositionId(long compositionId);
	
	Composition editComposition(EditCompositionForm editCompositionForm);
	void deleteByPieceIdAndCompositionPid(long pieceId, String compositionPid);

}

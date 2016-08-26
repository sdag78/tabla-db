package com.stevengiblin.spring.taleemdb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stevengiblin.spring.taleemdb.dto.CreateCompositionForm;
import com.stevengiblin.spring.taleemdb.dto.EditCompositionForm;
import com.stevengiblin.spring.taleemdb.entities.Composition;
import com.stevengiblin.spring.taleemdb.repositories.CompositionRepository;
import com.stevengiblin.spring.taleemdb.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CompositionServiceImpl implements CompositionService {

	private static final Logger logger = LoggerFactory.getLogger(CompositionServiceImpl.class);
	
	private CompositionRepository compositionRepository;
	
	@Autowired
	public CompositionServiceImpl(CompositionRepository compositionRepository) {
		this.compositionRepository = compositionRepository;
	}

	@Override
	public List<Composition> findByPieceId(long pieceId) {
		return compositionRepository.findByPieceId(pieceId);
	}

	@Override
	public List<Composition> findByPieceId(String pieceId) {
		return findByPieceId(Integer.parseInt(pieceId));
	}

	@Override
	public Composition createComposition(CreateCompositionForm createCompositionForm) {
		final Composition composition = new Composition();
		
		composition.setCompositionPid(getNewCompositionPID());
		composition.setPieceId(createCompositionForm.getPieceId());
		composition.setName(createCompositionForm.getName());
		composition.setBols(createCompositionForm.getBols());
		composition.setNotes(createCompositionForm.getComments());
		composition.setOrdering(createCompositionForm.getOrdering());
		composition.setUserId(MyUtil.getSessionUser().getId());
		
		compositionRepository.save(composition); 
		return composition;
	}

	private long getNewCompositionPID() {
		return 1 + compositionRepository.getMaxCompositionPidByUserId(MyUtil.getSessionUser().getId());
	}
	
	@Override
	public List<Composition> getCompositions(String pieceId) {
		return getCompositions(Long.parseLong(pieceId));
	}
	
	@Override
	public List<Composition> getCompositions(long pieceId) {
		return compositionRepository.findByPieceId(pieceId);
	}

	@Override
	public Composition findByPieceIdAndCompositionPid(long piecePid, String compositionPid) {
		return this.findByPieceIdAndCompositionPid(piecePid, Long.parseLong(compositionPid));
	}
	
	@Override
	public Composition findByPieceIdAndCompositionPid(long pieceId, long compositionPid) {
		return compositionRepository.findByPieceIdAndCompositionPidAndUserId(pieceId, compositionPid, MyUtil.getSessionUser().getId());
	}
	
	@Override
	public Composition findByCompositionId(String compositionId) {
		return compositionRepository.findOne(Long.parseLong(compositionId));
	}
	
	@Override
	public Composition findByCompositionId(long compositionId) {
		return compositionRepository.findOne(compositionId);
	}

	@Override
	public Composition editComposition(EditCompositionForm editCompositionForm) {
		
		long ecf_pieceId = editCompositionForm.getPieceId();
		long ecf_compositionPid = editCompositionForm.getCompositionPid();
		
		final Composition composition = findByPieceIdAndCompositionPid(
				ecf_pieceId,
				ecf_compositionPid);
		
		composition.setName(editCompositionForm.getName());
		composition.setBols(editCompositionForm.getBols());
		composition.setNotes(editCompositionForm.getComments());
		composition.setOrdering(editCompositionForm.getOrdering());
		
		compositionRepository.save(composition);
		return composition;
	}

	@Override
	public void deleteByPieceIdAndCompositionPid(long pieceId, String compositionPid) {
		Composition composition = findByCompositionId(compositionPid);
		compositionRepository.delete(composition);
	}

}

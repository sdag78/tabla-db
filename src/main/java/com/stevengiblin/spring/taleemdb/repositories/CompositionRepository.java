package com.stevengiblin.spring.taleemdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stevengiblin.spring.taleemdb.entities.Composition;

public interface CompositionRepository extends JpaRepository<Composition, Long> {

	List<Composition> findByPieceId(long pieceId);

	@Query("SELECT coalesce(max(t.compositionPid), 0) FROM #{#entityName} t where t.userId =?1")
	long getMaxCompositionPidByUserId(long userId);
	
	Composition findByPieceIdAndCompositionPidAndUserId(long pieceId, long compositionPid, long userId);
	
}

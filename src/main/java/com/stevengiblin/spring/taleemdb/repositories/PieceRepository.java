package com.stevengiblin.spring.taleemdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stevengiblin.spring.taleemdb.entities.Piece;

public interface PieceRepository extends JpaRepository<Piece, Long> {

	List<Piece> findByUserId(long id);

	Piece findByPiecePidAndUserId(long piecePid, long userId);

	// coalesce means if it is null, then replace with zero
	@Query("SELECT coalesce(max(t.piecePid),0) FROM #{#entityName} t where t.userId =?1")
	long getMaxPiecePidByUserId(long userId);
	
}

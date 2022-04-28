package com.history.tree.repositories;

import com.history.tree.model.Marriage;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MarriageRepository extends R2dbcRepository<Marriage, Long> {

    @Query("SELECT * from person p LEFT JOIN relationship r ON p.id=r.person_id LEFT JOIN marriage m ON r.marriage_id=m.id WHERE p.tree_id=:tree_id")
    Flux<Marriage> findRelationshipsByTree(@Param("tree_id") long treeId);

}

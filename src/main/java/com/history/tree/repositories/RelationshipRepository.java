package com.history.tree.repositories;

import com.history.tree.model.Relationship;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RelationshipRepository extends R2dbcRepository<Relationship, Long> {

    @Query("SELECT * from person p LEFT JOIN relationship r ON p.id= r.person_id WHERE p.tree_id=:tree_id")
    Flux<Relationship> findRelationshipsByTree(@Param("tree_id") long treeId);
}

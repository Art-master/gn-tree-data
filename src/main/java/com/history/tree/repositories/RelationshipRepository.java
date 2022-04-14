package com.history.tree.repositories;

import com.history.tree.model.Relationship;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends R2dbcRepository<Relationship, Long> {
}

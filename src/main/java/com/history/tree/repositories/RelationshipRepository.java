package com.history.tree.repositories;

import com.history.tree.model.Relationship;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RelationshipRepository extends R2dbcRepository<Relationship, Long> {
}

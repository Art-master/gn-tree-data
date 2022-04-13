package com.history.tree.repositories;

import com.history.tree.model.Tree;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TreeRepository extends R2dbcRepository<Tree, Long> {
}

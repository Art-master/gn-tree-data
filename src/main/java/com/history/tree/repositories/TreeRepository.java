package com.history.tree.repositories;

import com.history.tree.model.Tree;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TreeRepository extends R2dbcRepository<Tree, Long> {

    Mono<Long> removeById(Long id);

}

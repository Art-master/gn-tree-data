package com.history.tree.repositories;

import com.history.tree.model.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class TreeRepository {

    private final DatabaseClient client;

    public Flux<Tree> findAll() {
        return client.sql("select * from tree")
                .map(row -> new Tree(row.get("id", Long.class),
                        row.get("name", String.class))).all();
    }
}

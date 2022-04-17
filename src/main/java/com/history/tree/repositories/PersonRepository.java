package com.history.tree.repositories;

import com.history.tree.model.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends R2dbcRepository<Person, Long> {

    @NonNull
    Flux<Person> findAllByTreeId(@NonNull long treeId);
}

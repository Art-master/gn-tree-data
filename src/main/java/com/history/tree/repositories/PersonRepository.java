package com.history.tree.repositories;

import com.history.tree.model.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PersonRepository extends R2dbcRepository<Person, Long> {
}

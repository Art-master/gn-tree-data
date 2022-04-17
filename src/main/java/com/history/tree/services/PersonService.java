package com.history.tree.services;

import com.history.tree.model.Person;
import com.history.tree.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public Mono<Person> findById(long id) {
        return repository.findById(id);
    }

    public Flux<Person> getPersonsByTreeId(long treeId) {
        return repository.findAllByTreeId(treeId);
    }

}

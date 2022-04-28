package com.history.tree.services;

import com.history.tree.model.Marriage;
import com.history.tree.repositories.MarriageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MarriageService {

    private final MarriageRepository repository;

    public Mono<Marriage> findById(long id) {
        return repository.findById(id);
    }

    public Flux<Marriage> getPersonsByTreeId(long treeId) {
        return repository.findRelationshipsByTree(treeId);
    }

}

package com.history.tree.services;

import com.history.tree.dto.PersonDTO;
import com.history.tree.mappers.PersonMapper;
import com.history.tree.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public Mono<PersonDTO> findById(long id) {
        return repository.findById(id).map(PersonMapper.INSTANCE::personToDto);
    }

    public Flux<PersonDTO> getPersonsByTreeId(long treeId) {
        return repository.findAllByTreeId(treeId).map(PersonMapper.INSTANCE::personToDto);
    }

}

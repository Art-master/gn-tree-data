package com.history.tree.services;

import com.history.tree.dto.RelationshipDTO;
import com.history.tree.mappers.RelationshipMapper;
import com.history.tree.model.Relationship;
import com.history.tree.repositories.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository repository;

    public Mono<RelationshipDTO> findById(long id) {
        return null;
    }

    public Flux<RelationshipDTO> getByTreeId(long treeId) {
        return repository.findRelationshipsByTree(treeId).map(this::getDTO);
    }

    private RelationshipDTO getDTO(Relationship r) {
        return RelationshipMapper.INSTANCE.relationshipToDto(r);
    }
}

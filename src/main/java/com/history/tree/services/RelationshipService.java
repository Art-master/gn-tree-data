package com.history.tree.services;

import com.history.tree.dto.RelationshipDTO;
import com.history.tree.mappers.MarriageMapper;
import com.history.tree.mappers.RelationshipMapper;
import com.history.tree.model.Marriage;
import com.history.tree.model.Relationship;
import com.history.tree.repositories.MarriageRepository;
import com.history.tree.repositories.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository repository;
    private final MarriageService marriageService;

    public Mono<RelationshipDTO> findById(long id) {
        return null;
    }

    public Flux<RelationshipDTO> getByTreeId(long treeId) {
        var relationshipFlow = repository.findRelationshipsByTree(treeId);

        return marriageService.getPersonsByTreeId(treeId)
                .reduce(new HashMap<Long, Marriage>(), (map, m) -> {
                    map.put(m.getId(), m);
                    return map;
                })
                .flux()
                .zipWith(relationshipFlow, (marriages, r) -> {
                    var dto = new RelationshipDTO();
                    var marriage = marriages.get(r.getMarriageId());
                    var marriageDTO = MarriageMapper.INSTANCE.relationshipToDto(marriage);
                    dto.setMarriage(marriageDTO);
                    return new RelationshipDTO();
                });
    }
}

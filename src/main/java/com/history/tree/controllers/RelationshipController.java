package com.history.tree.controllers;

import com.history.tree.dto.RelationshipDTO;
import com.history.tree.services.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relationships")
public class RelationshipController {

    private final RelationshipService relationshipService;

    @GetMapping()
    public Mono<RelationshipDTO> getById(@RequestParam Long id) {
        return relationshipService.findById(id);
    }

    @GetMapping("get_by_tree")
    public Flux<RelationshipDTO> getAllByTreeId(@RequestParam Long treeId) {
        return relationshipService.getByTreeId(treeId);
    }
}

package com.history.tree.controllers;

import com.history.tree.dto.TreeDTO;
import com.history.tree.services.TreeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/trees")
public class TreeController {

    private final TreeService treeService;

    @GetMapping()
    public Mono<TreeDTO> getById(@RequestParam Long id) {
        return treeService.findById(id);
    }

    @GetMapping("get_by_user")
    public Mono<TreeDTO> getAllByUserId(@RequestParam Long id) {
        return treeService.findById(id);
    }

    @PostMapping()
    public Mono<TreeDTO> create(@RequestBody TreeDTO tree) {
        return treeService.create(tree);
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestParam Long id) {
        return treeService.delete(id);
    }
}

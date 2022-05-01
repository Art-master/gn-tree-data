package com.history.tree.services;

import com.history.tree.dto.TreeDTO;
import com.history.tree.mappers.TreeMapper;
import com.history.tree.model.Tree;
import com.history.tree.repositories.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TreeService {

    private final TreeRepository repository;

    public Mono<TreeDTO> findById(long id) {
        return repository.findById(id).map(TreeMapper.INSTANCE::entityToDTO);
    }

    public Flux<TreeDTO> findAll() {
        return repository.findAll().map(TreeMapper.INSTANCE::entityToDTO);
    }

    public Mono<TreeDTO> create(TreeDTO tree) {
        Tree entity = TreeMapper.INSTANCE.dtoToEntity(tree);
        return repository.save(entity).map(TreeMapper.INSTANCE::entityToDTO);
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}

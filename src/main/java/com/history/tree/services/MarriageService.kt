package com.history.tree.services

import com.history.tree.model.Marriage
import com.history.tree.repositories.MarriageRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class MarriageService(val repository: MarriageRepository) {

    suspend fun findById(id: Long): Marriage {
        return repository.findById(id)!!
    }

    suspend fun getByTreeId(treeId: Long): Flow<Marriage> {
        return repository.findRelationshipsByTree(treeId)
    }
}
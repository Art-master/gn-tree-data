package com.history.tree.repositories

import com.history.tree.model.Relationship
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RelationshipRepository : CoroutineCrudRepository<Relationship, Long> {

    @Query("SELECT * from person p LEFT JOIN relationship r ON p.id= r.person_id WHERE p.tree_id=:tree_id")
    suspend fun findRelationshipsByTree(@Param("tree_id") treeId: Long): Flow<Relationship>
}
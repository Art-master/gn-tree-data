package com.history.tree.repositories

import com.history.tree.model.Marriage
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MarriageRepository : CoroutineCrudRepository<Marriage, Long> {

    @Query("SELECT * from person p LEFT JOIN relationship r ON p.id=r.person_id LEFT JOIN marriage m ON r.marriage_id=m.id WHERE p.tree_id=:tree_id")
    suspend fun findMarriagesByTree(@Param("tree_id") treeId: Long): Flow<Marriage>
}
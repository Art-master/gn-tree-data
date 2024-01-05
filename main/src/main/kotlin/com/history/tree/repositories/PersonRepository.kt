package com.history.tree.repositories

import com.history.tree.model.Person
import com.history.tree.schemas.CommonSchema.id
import com.history.tree.schemas.PersonSchema.PERSON
import com.history.tree.schemas.PersonSchema.tree_view_id
import com.history.tree.schemas.PersonViewSchema.PERSON_VIEW
import com.history.tree.schemas.PersonViewSchema.person_id
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : CommonTreeRepository<Person>, CoroutineCrudRepository<Person, UUID> {

    @Query("DELETE FROM $PERSON p WHERE $tree_view_id=:treeViewId AND NOT EXISTS (SELECT * FROM $PERSON_VIEW pv WHERE pv.$person_id=p.$id)")
    suspend fun deleteByTreeViewIdIfViewsNotFound(treeViewId: Long)
}
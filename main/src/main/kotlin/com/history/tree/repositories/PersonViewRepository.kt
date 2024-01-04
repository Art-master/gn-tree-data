package com.history.tree.repositories

import com.history.tree.model.PersonView
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonViewRepository : CommonTreeRepository<PersonView>, CoroutineCrudRepository<PersonView, UUID>
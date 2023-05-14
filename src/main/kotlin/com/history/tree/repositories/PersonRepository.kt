package com.history.tree.repositories

import com.history.tree.model.Person
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : CommonTreeRepository<Person>, CoroutineCrudRepository<Person, UUID>
package com.history.tree.repositories

import com.history.tree.model.Marriage
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MarriageRepository : CommonTreeRepository<Marriage>, CoroutineCrudRepository<Marriage, UUID>
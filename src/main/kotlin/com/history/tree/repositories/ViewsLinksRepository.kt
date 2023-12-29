package com.history.tree.repositories

import com.history.tree.model.ViewsLinks
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ViewsLinksRepository : CommonTreeRepository<ViewsLinks>, CoroutineCrudRepository<ViewsLinks, UUID>

package com.history.tree.model

import com.history.tree.schemas.UserRoleSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(UserRoleSchema.TABLE_NAME)
class UserRole {

    @Id
    @Column(UserRoleSchema.ID)
    var id: Long = 0

    @Column(UserRoleSchema.NAME)
    var name: String = ""

    @Column(UserRoleSchema.USER_ID)
    var userId: Long = 0

}
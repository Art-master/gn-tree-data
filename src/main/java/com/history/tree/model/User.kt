package com.history.tree.model

import com.history.tree.schemas.UserSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*


@Table(UserSchema.TABLE_NAME)
class User {

    @Id
    @Column(UserSchema.ID)
    var id: Long = 0

    @Column(UserSchema.NAME)
    var name: String = ""

    @Column(UserSchema.LAST_NAME)
    var lastName: String = ""

    @Column(UserSchema.LOGIN)
    var login: String = ""

    @Column(UserSchema.PASSWORD_HASH)
    var password: String = ""

    @Column(UserSchema.AVATAR_ID)
    var avatarId: Long = 0

    @Column(UserSchema.LANG)
    var lang: String = Locale.getDefault().toLanguageTag()

}
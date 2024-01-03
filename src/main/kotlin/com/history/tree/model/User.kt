package com.history.tree.model

import com.history.tree.schemas.PersonSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table(PersonSchema.PERSON)
class User(

    @Id
    @Column(PersonSchema.ID)
    var id: Long = 0,

    @Column(PersonSchema.NAME)
    var name: String = "",

    //@Column(UserSchema.LAST_NAME)
    // var lastName: String = "",

    @Column(PersonSchema.LOGIN)
    var login: String = "",

    @Column(PersonSchema.PASSWORD_HASH)
    var password: String = "",

    //@Column(UserSchema.AVATAR_ID)
    //var avatarId: Long = 0,

    //@Column(UserSchema.LANG)
    // var lang: String = Locale.getDefault().toLanguageTag()

)
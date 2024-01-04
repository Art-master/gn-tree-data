package com.history.tree.extension

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt

object Auth {
    fun Authentication.getUserId(): String {
        return (principal as Jwt).claims["system_user_id"].toString()
    }
}
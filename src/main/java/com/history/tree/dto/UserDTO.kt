package com.history.tree.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDTO : UserDetails {

    var id: Long = 0
    var name: String = ""
    var lastName: String = ""

    var login: String = ""
    override fun getUsername() = login

    private var password: String = ""
    override fun getPassword() = password

    var avatarId: Long = 0

    var lang: String = Locale.getDefault().toLanguageTag()

    val authorities: List<String> = emptyList()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

}
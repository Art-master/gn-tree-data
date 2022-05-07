package com.history.tree.mappers

import com.history.tree.dto.UserDTO
import com.history.tree.model.User
import org.mapstruct.Mapper

@Mapper
interface UserMapper : EntityMapper<User, UserDTO>
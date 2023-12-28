package com.history.tree.mappers

import com.history.tree.dto.UserDto
import com.history.tree.model.User
import org.mapstruct.Mapper

@Mapper
interface UserMapper : EntityMapper<User, UserDto>
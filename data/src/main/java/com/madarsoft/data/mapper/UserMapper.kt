package com.madarsoft.data.mapper

import com.madarsoft.core.model.User
import com.madarsoft.data.entity.UserEntity

object UserMapper{

    fun fromUserEntityToUser(userEntity: UserEntity):User{
        return User(
            userEntity.name,
            userEntity.age,
            userEntity.jobTitle,
            userEntity.gender,
        )
    }

    fun fromUserToUserEntity(user: User):UserEntity{
        return UserEntity(
            name = user.name,
            age = user.age,
            jobTitle = user.jobTitle,
            gender = user.gender
        )
    }
}
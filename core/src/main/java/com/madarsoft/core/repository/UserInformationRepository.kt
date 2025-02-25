package com.madarsoft.core.repository

import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User

interface UserInformationRepository {
    suspend fun addNewUserInformation(user: User):Result<User>

    suspend fun getUserInformation():Result<User>
}
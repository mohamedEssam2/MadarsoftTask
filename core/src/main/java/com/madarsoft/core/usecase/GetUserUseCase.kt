package com.madarsoft.core.usecase

import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User
import com.madarsoft.core.repository.UserInformationRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userInformationRepository: UserInformationRepository){

    suspend fun invoke(): Result<User> {
        return userInformationRepository.getUserInformation()
    }
}
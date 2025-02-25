package com.madarsoft.core.usecase

import android.util.Log
import com.madarsoft.core.exception.DatabaseError
import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User
import com.madarsoft.core.repository.UserInformationRepository
import javax.inject.Inject

class AddNewUserUseCase @Inject constructor(private val userInformationRepository: UserInformationRepository) {

    suspend fun invoke(user: User): Result<User> {
        if (!isNameValid(user)) {
            return Result.Error(DatabaseError.InvalidDataException("the value of name is invalid data"))
        } else if (!isJobTitleValid(user)) {
            return Result.Error(DatabaseError.InvalidDataException("the value of job title is invalid data"))
        } else {
            return userInformationRepository.addNewUserInformation(user)
        }
    }

    private fun isNameValid(user: User): Boolean {
        if (user.name != null && !user.name.isEmpty() && isCorrectData(user.name)) {
            return true
        }
        return false
    }

    private fun isJobTitleValid(user: User): Boolean {
        if (user.jobTitle != null && !user.jobTitle.isEmpty() && isCorrectData(user.jobTitle)) {
            return true
        }
        return false
    }

    private fun isCorrectData(input: String): Boolean {
        val regex = "^[A-Za-z ]+$".toRegex()
        return regex.matches(input)
    }
}
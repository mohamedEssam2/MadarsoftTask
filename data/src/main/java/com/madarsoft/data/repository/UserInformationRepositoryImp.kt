package com.madarsoft.data.repository

import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User
import com.madarsoft.core.repository.UserInformationRepository
import com.madarsoft.data.local_database.UserInformationDao
import com.madarsoft.data.mapper.DatabaseErrorMapper
import com.madarsoft.data.mapper.UserMapper
import javax.inject.Inject

class UserInformationRepositoryImp @Inject constructor(val userInformationDao: UserInformationDao) :
    UserInformationRepository {
    override suspend fun addNewUserInformation(user: User): Result<User> {
       try {
           this.userInformationDao.addNewUserInformation(UserMapper.fromUserToUserEntity(user))
           return Result.Success(user)
       }catch (exception:Exception){
           return Result.Error(DatabaseErrorMapper.getDatabaseError(exception))
       }
    }

    override suspend fun getUserInformation(): Result<User> {
        try {
            val userEntity = this.userInformationDao.getUserInformation().get(0)
            return Result.Success(UserMapper.fromUserEntityToUser(userEntity))
        }catch (exception:Exception){
            return Result.Error(DatabaseErrorMapper.getDatabaseError(exception))
        }
    }
}
package com.madarsoft.task.di

import com.madarsoft.core.repository.UserInformationRepository
import com.madarsoft.data.local_database.UserInformationDao
import com.madarsoft.data.repository.UserInformationRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserInformationRepositoryModule {

    @Provides
    fun provideUserInformationRepository(userInformationDao: UserInformationDao):UserInformationRepository{
        return UserInformationRepositoryImp(userInformationDao)
    }
}
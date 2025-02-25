package com.madarsoft.task.di

import com.madarsoft.core.repository.UserInformationRepository
import com.madarsoft.core.usecase.AddNewUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object InputScreenModule {

    @Provides
    fun provideAddNewUserUseCase(userInformationRepository: UserInformationRepository):AddNewUserUseCase{
        return AddNewUserUseCase(userInformationRepository)
    }
}
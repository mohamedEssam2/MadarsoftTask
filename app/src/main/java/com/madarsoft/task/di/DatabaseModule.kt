package com.madarsoft.task.di

import android.content.Context
import androidx.room.Room
import com.madarsoft.data.local_database.UserInformationDao
import com.madarsoft.data.local_database.UserInformationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): UserInformationDatabase {
        return Room.databaseBuilder(context, UserInformationDatabase::class.java, "UserInformationDatabase")
            .build()
    }

    @Provides
    fun provideUserInformationDao(userInformationDatabase: UserInformationDatabase): UserInformationDao {
        return userInformationDatabase.getUserInformationDao()
    }

}
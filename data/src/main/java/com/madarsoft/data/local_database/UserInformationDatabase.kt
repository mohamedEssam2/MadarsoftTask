package com.madarsoft.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madarsoft.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserInformationDatabase : RoomDatabase() {
    abstract fun getUserInformationDao(): UserInformationDao
}
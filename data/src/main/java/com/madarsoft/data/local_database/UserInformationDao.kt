package com.madarsoft.data.local_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madarsoft.data.entity.UserEntity

@Dao
interface UserInformationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addNewUserInformation(userEntity: UserEntity)

    @Query("SELECT * FROM user_table WHERE id = 1 LIMIT 1")
    suspend fun getUserInformation():List<UserEntity>
}
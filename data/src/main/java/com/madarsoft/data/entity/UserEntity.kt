package com.madarsoft.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id:Int=1,
    val name:String,
    val age:Float,
    val jobTitle:String,
    val gender:String
)


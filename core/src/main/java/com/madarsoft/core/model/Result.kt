package com.madarsoft.core.model

import com.madarsoft.core.exception.DatabaseError

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: DatabaseError) : Result<Nothing>()
}
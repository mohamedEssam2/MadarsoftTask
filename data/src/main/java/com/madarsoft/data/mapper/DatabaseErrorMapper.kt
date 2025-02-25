package com.madarsoft.data.mapper

import com.madarsoft.core.exception.DatabaseError
import java.lang.Exception

object DatabaseErrorMapper {
    fun getDatabaseError(exception: Exception): DatabaseError {
        return when(exception){
            is java.io.IOException ->{
                DatabaseError.IOException(exception.message?:"java.io.IOException")
            }

            is java.util.concurrent.CancellationException ->{
                DatabaseError.CancellationException(exception.message?:"java.util.concurrent.CancellationException")
            }

            is IllegalStateException ->{
                DatabaseError.IllegalStateException(exception.message?:"IllegalStateException")
            }

            is android.database.sqlite.SQLiteDatabaseCorruptException ->{
                DatabaseError.SQLiteDatabaseCorruptException(exception.message?:"android.database.sqlite.SQLiteDatabaseCorruptException")
            }

            is android.database.sqlite.SQLiteFullException ->{
                DatabaseError.SQLiteFullException(exception.message?:"android.database.sqlite.SQLiteFullException")
            }

            is android.database.sqlite.SQLiteAbortException ->{
                DatabaseError.SQLiteAbortException(exception.message?:"android.database.sqlite.SQLiteAbortException")
            }

            is java.lang.RuntimeException ->{
                DatabaseError.RuntimeException(exception.message?:"java.lang.RuntimeException")
            }

            else ->{
                DatabaseError.TransactionException(exception.message?:"TransactionException")
            }
        }
    }
}
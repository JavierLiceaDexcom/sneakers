package com.xavidev.testessential.data.source.local.dao

import androidx.room.*
import com.xavidev.testessential.data.source.local.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE id =:id")
    suspend fun getUser(id: String): User

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User): Int
}
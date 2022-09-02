package com.xavidev.testessential.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xavidev.testessential.data.entity.User

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Int

    @Query("SELECT * FROM user WHERE id =:id")
    suspend fun getUser(id: String): User

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User): Int
}
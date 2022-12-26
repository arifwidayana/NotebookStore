package com.arifwidayana.notebookstore.data.local.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Query("SELECT * FROM user_table WHERE name = :name AND password = :password")
    fun loginUser(name: String?, password: String?): Flow<UserEntity>

    @Query("SELECT * FROM user_table WHERE name = :name")
    fun getUser(name: String?): Flow<UserEntity>
}
package com.arifwidayana.notebookstore.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val name: String,
    val password: String?,
    val role: RoleUser?
)

package com.arifwidayana.notebookstore.data.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arifwidayana.notebookstore.data.local.model.entity.RoleUser
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DatabaseSeederCallback(private val context: Context) : RoomDatabase.Callback() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch {
            StoreDatabase.getInstance(context).userDao().insertUser(prepopulateUser())
        }
    }

    private fun prepopulateUser(): List<UserEntity> {
        return mutableListOf(
            UserEntity(name = "Admin", password = "admin", role = RoleUser.ADMIN),
            UserEntity(name = "Ari", password = "ari", role = RoleUser.EMPLOYEE),
            UserEntity(name = "Bagas", password = "bagas", role = RoleUser.EMPLOYEE),
            UserEntity(name = "Cika", password = "cika", role = RoleUser.EMPLOYEE),
            UserEntity(name = "Dimas", password = "dimas", role = RoleUser.EMPLOYEE),
            UserEntity(name = "Fika", password = "fika", role = RoleUser.EMPLOYEE)
        )
    }
}
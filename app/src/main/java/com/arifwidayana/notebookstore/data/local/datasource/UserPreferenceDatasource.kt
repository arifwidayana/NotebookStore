package com.arifwidayana.notebookstore.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arifwidayana.notebookstore.common.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserPreferenceDatasource {
    suspend fun setName(name: String)
    suspend fun getName(): Flow<String>
    suspend fun logoutUser()
}

class UserPreferenceDatasourceImpl @Inject constructor(
    private val context: Context
): UserPreferenceDatasource {
    override suspend fun setName(name: String) {
        context.dataStore.edit {
            it[namePref] = name
        }
    }

    override suspend fun getName(): Flow<String> {
        return context.dataStore.data.map {
            it[namePref] ?: Constant.NAME_PREF
        }
    }

    override suspend fun logoutUser() {
        context.dataStore.edit {
            it.remove(namePref)
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(Constant.DATASTORE_PREF)
        private val namePref = stringPreferencesKey(Constant.NAME_PREF)
    }
}
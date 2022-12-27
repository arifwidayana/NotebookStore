package com.arifwidayana.notebookstore.data.local.repository

import com.arifwidayana.notebookstore.common.base.BaseRepository
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ProfileRepository {
    suspend fun getName(): Flow<Resource<String>>
    suspend fun getUser(name: String): Flow<Resource<UserEntity>>
    suspend fun logoutUser(): Flow<Resource<Unit>>
}

class ProfileRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource,
    private val userPreferenceDatasource: UserPreferenceDatasource
): ProfileRepository, BaseRepository() {
    override suspend fun getName(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDatasource.getName().first() })
    }

    override suspend fun getUser(name: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDatasource.getUser(name).first() })
    }

    override suspend fun logoutUser(): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.logoutUser() })
    }
}
package com.arifwidayana.notebookstore.data.local.repository

import com.arifwidayana.notebookstore.common.base.BaseRepository
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainRepository {
    suspend fun getItem(): Flow<Resource<List<StoreEntity>>>
    suspend fun getName(): Flow<Resource<String>>
    suspend fun getUser(name: String): Flow<Resource<UserEntity>>
}

class MainRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource,
    private val userPreferenceDatasource: UserPreferenceDatasource
): MainRepository, BaseRepository() {
    override suspend fun getItem(): Flow<Resource<List<StoreEntity>>> = flow {
        emit(proceed { localDatasource.getItem().first() })
    }

    override suspend fun getName(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDatasource.getName().first() })
    }

    override suspend fun getUser(name: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDatasource.getUser(name).first() })
    }
}
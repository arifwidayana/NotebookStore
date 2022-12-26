package com.arifwidayana.notebookstore.data.local.repository

import com.arifwidayana.notebookstore.common.base.BaseRepository
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>>
    suspend fun setName(name: String): Flow<Resource<Unit>>
}

class LoginRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource,
    private val userPreferenceDatasource: UserPreferenceDatasource
) : LoginRepository, BaseRepository() {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDatasource.loginUser(loginRequest).first() })
    }

    override suspend fun setName(name: String): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.setName(name) })
    }
}
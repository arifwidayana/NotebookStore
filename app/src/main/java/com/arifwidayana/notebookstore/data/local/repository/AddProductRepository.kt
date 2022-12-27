package com.arifwidayana.notebookstore.data.local.repository

import com.arifwidayana.notebookstore.common.base.BaseRepository
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.model.request.store.InsertItemRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AddProductRepository {
    suspend fun insertItem(insertItemRequest: InsertItemRequest): Flow<Resource<Unit>>
}

class AddProductRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource
) : AddProductRepository, BaseRepository() {
    override suspend fun insertItem(insertItemRequest: InsertItemRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDatasource.insertItem(insertItemRequest) })
    }
}
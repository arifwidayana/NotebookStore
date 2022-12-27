package com.arifwidayana.notebookstore.data.local.repository

import com.arifwidayana.notebookstore.common.base.BaseRepository
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.request.store.UpdateItemRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface EditProductRepository {
    suspend fun getItemById(idProduct: Int): Flow<Resource<StoreEntity>>
    suspend fun updateItemStore(updateItemRequest: UpdateItemRequest): Flow<Resource<Unit>>
    suspend fun deleteItemById(idProduct: Int): Flow<Resource<Unit>>
}

class EditProductRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource
): EditProductRepository, BaseRepository() {
    override suspend fun getItemById(idProduct: Int): Flow<Resource<StoreEntity>> = flow {
        emit(proceed { localDatasource.getItemById(idProduct).first() })
    }

    override suspend fun updateItemStore(updateItemRequest: UpdateItemRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDatasource.updateItemStore(updateItemRequest) })
    }

    override suspend fun deleteItemById(idProduct: Int): Flow<Resource<Unit>> = flow {
        emit(proceed { localDatasource.deleteItemById(idProduct) })
    }

}
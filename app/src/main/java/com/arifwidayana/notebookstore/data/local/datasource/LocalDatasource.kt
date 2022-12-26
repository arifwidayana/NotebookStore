package com.arifwidayana.notebookstore.data.local.datasource

import com.arifwidayana.notebookstore.data.local.model.dao.StoreDao
import com.arifwidayana.notebookstore.data.local.model.dao.UserDao
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.model.request.store.InsertItemRequest
import com.arifwidayana.notebookstore.data.local.model.request.store.UpdateItemRequest
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDatasource {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity>
    suspend fun getUser(name: String): Flow<UserEntity>
    suspend fun insertItem(insertItemRequest: InsertItemRequest)
    suspend fun getItem(): Flow<List<StoreEntity>>
    suspend fun getItemById(idItem: Int): Flow<StoreEntity>
    suspend fun deleteItemById(idItem: Int)
    suspend fun updateItemStore(updateItemRequest: UpdateItemRequest)
}

class LocalDatasourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val storeDao: StoreDao
) : LocalDatasource {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity> {
        return userDao.loginUser(
            name = loginRequest.name,
            password = loginRequest.password
        )
    }

    override suspend fun getUser(name: String): Flow<UserEntity> {
        return userDao.getUser(name)
    }

    override suspend fun insertItem(insertItemRequest: InsertItemRequest) {
        return storeDao.insertItem(
            StoreEntity(
                id = null,
                name = insertItemRequest.name,
                amountItem = insertItemRequest.amountItem,
                supplier = insertItemRequest.supplier,
                createdAt = insertItemRequest.createdAt,
                updatedAt = insertItemRequest.updatedAt
            )
        )
    }

    override suspend fun getItem(): Flow<List<StoreEntity>> {
        return storeDao.getItem()
    }

    override suspend fun getItemById(idItem: Int): Flow<StoreEntity> {
        return storeDao.getItemById(idItem)
    }

    override suspend fun deleteItemById(idItem: Int) {
        return storeDao.deleteItemById(idItem)
    }

    override suspend fun updateItemStore(updateItemRequest: UpdateItemRequest) {
        return storeDao.updateItemStore(
            idItem = updateItemRequest.idItem,
            name = updateItemRequest.name,
            amountItem = updateItemRequest.amountItem,
            updatedAt = updateItemRequest.updatedAt
        )
    }
}
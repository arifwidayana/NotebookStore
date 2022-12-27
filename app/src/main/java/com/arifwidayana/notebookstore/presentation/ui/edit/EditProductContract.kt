package com.arifwidayana.notebookstore.presentation.ui.edit

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.request.store.UpdateItemRequest
import kotlinx.coroutines.flow.StateFlow

interface EditProductContract {
    val getItemByIdResult: StateFlow<Resource<StoreEntity>>
    val updateItemStoreResult: StateFlow<Resource<Unit>>
    val deleteItemByIdResult: StateFlow<Resource<Unit>>
    fun getItemById(idProduct: Int)
    fun updateItemStore(updateItemRequest: UpdateItemRequest)
    fun deleteItemById(idProduct: Int)
}
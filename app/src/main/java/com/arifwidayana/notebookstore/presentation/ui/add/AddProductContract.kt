package com.arifwidayana.notebookstore.presentation.ui.add

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.request.store.InsertItemRequest
import kotlinx.coroutines.flow.StateFlow

interface AddProductContract {
    val insertProductResult: StateFlow<Resource<Unit>>
    fun insertProduct(insertItemRequest: InsertItemRequest)
}
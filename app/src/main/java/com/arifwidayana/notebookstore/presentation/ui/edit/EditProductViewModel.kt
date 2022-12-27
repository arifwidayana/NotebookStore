package com.arifwidayana.notebookstore.presentation.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.request.store.UpdateItemRequest
import com.arifwidayana.notebookstore.data.local.repository.EditProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val editProductRepository: EditProductRepository
): EditProductContract, ViewModel() {
    private val _getItemByIdResult = MutableStateFlow<Resource<StoreEntity>>(Resource.Empty())
    private val _updateItemStoreResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    private val _deleteItemByIdResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val getItemByIdResult: StateFlow<Resource<StoreEntity>> = _getItemByIdResult
    override val updateItemStoreResult: StateFlow<Resource<Unit>> = _updateItemStoreResult
    override val deleteItemByIdResult: StateFlow<Resource<Unit>> = _deleteItemByIdResult

    override fun getItemById(idProduct: Int) {
        viewModelScope.launch {
            editProductRepository.getItemById(idProduct).collect {
                _getItemByIdResult.value = Resource.Success(it.data)
            }
        }
    }

    override fun updateItemStore(updateItemRequest: UpdateItemRequest) {
        viewModelScope.launch {
            editProductRepository.updateItemStore(updateItemRequest).collect {
                _updateItemStoreResult.value = Resource.Success(message = "Updated has success")
            }
        }
    }

    override fun deleteItemById(idProduct: Int) {
        viewModelScope.launch {
            editProductRepository.deleteItemById(idProduct).collect {
                _deleteItemByIdResult.value = Resource.Success(message = "Product has been deleted")
            }
        }
    }
}
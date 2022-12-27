package com.arifwidayana.notebookstore.presentation.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.request.store.InsertItemRequest
import com.arifwidayana.notebookstore.data.local.repository.AddProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductRepository: AddProductRepository
) : AddProductContract, ViewModel() {
    private val _insertProductResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val insertProductResult: StateFlow<Resource<Unit>> = _insertProductResult

    override fun insertProduct(insertItemRequest: InsertItemRequest) {
        viewModelScope.launch {
            addProductRepository.insertItem(insertItemRequest).collect {
                _insertProductResult.value = Resource.Success(message = "Product has been added")
            }
        }
    }
}
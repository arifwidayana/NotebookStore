package com.arifwidayana.notebookstore.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): MainContract, ViewModel() {
    private val _getNameResult = MutableStateFlow<Resource<String>>(Resource.Empty())
    private val _getUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    private val _getItemResult = MutableStateFlow<Resource<List<StoreEntity>>>(Resource.Empty())
    override val getNameResult: StateFlow<Resource<String>> = _getNameResult
    override val getUserResult: StateFlow<Resource<UserEntity>> = _getUserResult
    override val getItemResult: MutableStateFlow<Resource<List<StoreEntity>>> = _getItemResult

    override fun getName() {
        viewModelScope.launch {
            mainRepository.getName().collect {
                _getNameResult.value = Resource.Success(it.data)
                mainRepository.getUser(it.data.toString()).collect { res ->
                    _getUserResult.value = Resource.Success(res.data)
                }
            }
        }
    }

    override fun getItem() {
        viewModelScope.launch {
            mainRepository.getItem().collect {
                _getItemResult.value = Resource.Success(it.data)
            }
        }
    }
}
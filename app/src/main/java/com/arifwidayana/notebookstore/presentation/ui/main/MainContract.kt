package com.arifwidayana.notebookstore.presentation.ui.main

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface MainContract {
    val getNameResult: StateFlow<Resource<String>>
    val getUserResult: StateFlow<Resource<UserEntity>>
    val getItemResult: MutableStateFlow<Resource<List<StoreEntity>>>
    fun getName()
    fun getItem()
}
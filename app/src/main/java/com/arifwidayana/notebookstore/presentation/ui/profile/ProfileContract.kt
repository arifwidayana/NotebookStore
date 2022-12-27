package com.arifwidayana.notebookstore.presentation.ui.profile

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.StateFlow

interface ProfileContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    fun getUser()
    fun logoutUser()
}
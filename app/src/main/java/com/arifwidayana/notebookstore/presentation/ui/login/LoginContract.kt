package com.arifwidayana.notebookstore.presentation.ui.login

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    val loginUserResult: StateFlow<Resource<UserEntity>>
    val getNameResult: StateFlow<Resource<Unit>>
    fun getName()
    fun loginUser(loginRequest: LoginRequest)
}
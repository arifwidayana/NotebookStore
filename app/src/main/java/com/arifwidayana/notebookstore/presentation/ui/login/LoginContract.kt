package com.arifwidayana.notebookstore.presentation.ui.login

import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    val loginUserResult: StateFlow<Resource<UserEntity>>
    fun loginUser(loginRequest: LoginRequest)
}
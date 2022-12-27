package com.arifwidayana.notebookstore.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.notebookstore.common.utils.Constant
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import com.arifwidayana.notebookstore.data.local.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginContract, ViewModel() {
    private val _loginUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    private val _getNameResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val loginUserResult: StateFlow<Resource<UserEntity>> = _loginUserResult
    override val getNameResult: StateFlow<Resource<Unit>> = _getNameResult

    override fun getName() {
        viewModelScope.launch {
            loginRepository.getName().collect {
                if (it.data != Constant.NAME_PREF) {
                    _getNameResult.value = Resource.Success()
                } else {
                    _getNameResult.value = Resource.Error()
                }
            }
        }
    }

    override fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginRepository.loginUser(loginRequest).collect {
                if (it.data?.name == loginRequest.name && it.data.password == loginRequest.password) {
                    loginRepository.setName(loginRequest.name).first()
                    _loginUserResult.value = Resource.Success()
                } else {
                    _loginUserResult.value =
                        Resource.Error(message = "Username and password is wrong")
                }
            }
        }
    }
}
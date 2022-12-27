package com.arifwidayana.notebookstore.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.data.local.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ProfileContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    override val getUserResult: StateFlow<Resource<UserEntity>> = _getUserResult

    override fun getUser() {
        viewModelScope.launch {
            profileRepository.getName().collect {
                profileRepository.getUser(it.data.toString()).collect { res ->
                    _getUserResult.value = Resource.Success(res.data)
                }
            }
        }
    }

    override fun logoutUser() {
        viewModelScope.launch {
            profileRepository.logoutUser().collect {
                profileRepository.logoutUser().first()
            }
        }
    }
}
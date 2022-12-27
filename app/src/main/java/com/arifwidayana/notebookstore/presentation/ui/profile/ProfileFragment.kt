package com.arifwidayana.notebookstore.presentation.ui.profile

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.notebookstore.common.base.BaseDialogFragment
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.databinding.FragmentProfileBinding
import com.arifwidayana.notebookstore.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseDialogFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getUser()
    }

    private fun onClick() {
        binding.apply {
            sivClose.setOnClickListener {
                dismiss()
            }
            btnLogout.setOnClickListener {
                viewModel.logoutUser()
                requireActivity().run {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.getUserResult.collect {
                    if (it is Resource.Success) {
                        setView(it.data)
                    }
                }
            }
        }
    }

    private fun setView(data: UserEntity?) {
        binding.apply {
            data?.let {
                tvNameEmployee.text = it.name
                tvRoleEmployee.text = it.role.toString()
            }
        }
    }
}
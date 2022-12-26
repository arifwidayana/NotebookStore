package com.arifwidayana.notebookstore.presentation.ui.login

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.notebookstore.common.base.BaseActivity
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.request.user.LoginRequest
import com.arifwidayana.notebookstore.databinding.ActivityLoginBinding
import com.arifwidayana.notebookstore.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    ActivityLoginBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.loginUser(
                    LoginRequest(
                        name = etName.text.toString(),
                        password = etPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginUserResult.collect {
                if(it is Resource.Success) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else if (it is Resource.Error) {
                    showMessageSnackBar(true, it.message)
                }
            }
        }
    }
}
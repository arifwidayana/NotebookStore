package com.arifwidayana.notebookstore.presentation.ui.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.notebookstore.common.base.BaseActivity
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.RoleUser
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.entity.UserEntity
import com.arifwidayana.notebookstore.databinding.ActivityMainBinding
import com.arifwidayana.notebookstore.presentation.adapter.MainAdapter
import com.arifwidayana.notebookstore.presentation.ui.add.AddProductFragment
import com.arifwidayana.notebookstore.presentation.ui.edit.EditProductFragment
import com.arifwidayana.notebookstore.presentation.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {
    private lateinit var role: RoleUser

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.apply {
            getName()
            getItem()
        }
    }

    private fun onClick() {
        binding.apply {
            ivAccount.setOnClickListener {
                ProfileFragment().show(supportFragmentManager, null)
            }
            fabAdd.setOnClickListener {
                AddProductFragment().show(supportFragmentManager, null)
            }
            swipeRefreshLayout.setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getItem()
                    swipeRefreshLayout.isRefreshing = false
                }, 2000)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.getNameResult.collect {
                    if (it is Resource.Success) {
                        binding.tvName.text = it.data
                    }
                }
            }

            launchWhenStarted {
                viewModel.getUserResult.collect {
                    if (it is Resource.Success) {
                        setView(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModel.getItemResult.asLiveData().observe(this@MainActivity) {
                    if (it is Resource.Success) {
                        Log.d("DATA", it.data.toString())
                        setAdapter(it.data)
                    }
                }
            }
        }
    }

    private fun setView(data: UserEntity?) {
        binding.apply {
            data?.role?.let {
                role = it
                if (role == RoleUser.EMPLOYEE) {
                    fabAdd.visibility = View.GONE
                } else {
                    fabAdd.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setAdapter(data: List<StoreEntity>?) {
        binding.apply {
            val adapter = MainAdapter {
                it.id?.let { res ->
                    if (role == RoleUser.EMPLOYEE) {
                        showMessageSnackBar(true, "You don't have permission")
                    } else {
                        EditProductFragment(res).show(supportFragmentManager, null)
                    }
                }
            }
            adapter.submitList(data)
            rvItemStore.adapter = adapter
        }
    }
}
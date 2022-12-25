package com.arifwidayana.notebookstore.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseActivity<VB: ViewBinding, VM: ViewModel>(
    private val bindingFactory: (LayoutInflater) -> VB
): BaseContract, AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    @Inject
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initView()
        observeData()
    }

    abstract fun initView()
    abstract fun observeData()

    override fun showContent(isVisible: Boolean) { }
    override fun showLoading(isVisible: Boolean) { }

    override fun showMessageToast(isEnabled: Boolean, message: String?) {
        when{
            isEnabled -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showMessageSnackBar(isEnabled: Boolean, message: String?) {
        when{
            isEnabled -> {
                Snackbar.make(binding.root, message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.arifwidayana.notebookstore.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseDialogFragment<VB: ViewBinding, VM: ViewModel>(
    private val bindingFactory: (LayoutInflater) -> VB
): BaseContract.DialogFragment, DialogFragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    @Inject
    protected lateinit var viewModel: VM

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingFactory.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    abstract fun initView()
    abstract fun observeData()

    override fun showMessageToast(isEnabled: Boolean, message: String?) {
        when{
            isEnabled -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
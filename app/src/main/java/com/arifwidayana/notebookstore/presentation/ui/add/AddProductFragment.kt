package com.arifwidayana.notebookstore.presentation.ui.add

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.notebookstore.common.base.BaseDialogFragment
import com.arifwidayana.notebookstore.common.utils.pickDateTimeNow
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.request.store.InsertItemRequest
import com.arifwidayana.notebookstore.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BaseDialogFragment<FragmentAddProductBinding, AddProductViewModel>(
    FragmentAddProductBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.btnSave.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        binding.apply {
            viewModel.insertProduct(
                InsertItemRequest(
                    name = etNameProduct.text.toString(),
                    amountItem = etAmountProduct.text.toString().toInt(),
                    supplier = etSupplier.text.toString(),
                    createdAt = pickDateTimeNow(),
                    updatedAt = pickDateTimeNow()
                )
            )
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.insertProductResult.collect {
                if(it is Resource.Success) {
                    showMessageToast(true, it.message)
                    dismiss()
                }
            }
        }
    }
}
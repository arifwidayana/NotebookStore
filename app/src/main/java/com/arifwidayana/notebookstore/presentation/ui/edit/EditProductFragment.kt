package com.arifwidayana.notebookstore.presentation.ui.edit

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.notebookstore.common.base.BaseDialogFragment
import com.arifwidayana.notebookstore.common.utils.pickDateTimeNow
import com.arifwidayana.notebookstore.common.wrapper.Resource
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.data.local.model.request.store.UpdateItemRequest
import com.arifwidayana.notebookstore.databinding.FragmentEditProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProductFragment(private val idProductItem: Int) : BaseDialogFragment<FragmentEditProductBinding, EditProductViewModel>(
    FragmentEditProductBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.apply {
            getItemById(idProductItem)
        }
    }

    private fun onClick() {
        binding.apply {
            btnSubmit.setOnClickListener {
                viewModel.updateItemStore(
                    UpdateItemRequest(
                        idItem = idProductItem,
                        name = etNameProduct.text.toString(),
                        amountItem = etAmountProduct.text.toString().toInt(),
                        updatedAt = pickDateTimeNow()
                    )
                )
            }
            btnDelete.setOnClickListener {
                viewModel.deleteItemById(idProductItem)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.getItemByIdResult.collect {
                    if (it is Resource.Success) {
                        setView(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModel.updateItemStoreResult.collect {
                    if (it is Resource.Success) {
                        showMessageToast(true, it.message)
                        dismiss()
                    }
                }
            }

            launchWhenStarted {
                viewModel.deleteItemByIdResult.collect {
                    if (it is Resource.Success) {
                        showMessageToast(true, it.message)
                        dismiss()
                    }
                }
            }
        }
    }

    private fun setView(data: StoreEntity?) {
        binding.apply {
            data?.let {
                etNameProduct.setText(it.name)
                etAmountProduct.setText(it.amountItem.toString())
                etSupplier.setText(it.supplier)
                tvUpdatedTime.text = it.updatedAt
                tvAddedTime.text = it.createdAt
            }
        }
    }
}
package com.arifwidayana.notebookstore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import com.arifwidayana.notebookstore.databinding.CardItemStoreBinding

class MainAdapter(private val onClick: (StoreEntity) -> Unit) :
    ListAdapter<StoreEntity, MainAdapter.StoreHolder>(
        Differ()
    ) {
    class StoreHolder(
        private val binding: CardItemStoreBinding,
        private val onClick: (StoreEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun result(currentProduct: StoreEntity) {
            binding.apply {
                tvProductName.text = currentProduct.name
                tvAmountItem.text = currentProduct.amountItem.toString()
                tvSupplierItem.text = currentProduct.supplier
                tvUpdatedTime.text = currentProduct.updatedAt
                root.setOnClickListener {
                    onClick(currentProduct)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<StoreEntity>() {
        override fun areItemsTheSame(
            oldItem: StoreEntity,
            newItem: StoreEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: StoreEntity,
            newItem: StoreEntity
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        val binding =
            CardItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        holder.result(getItem(position))
    }
}
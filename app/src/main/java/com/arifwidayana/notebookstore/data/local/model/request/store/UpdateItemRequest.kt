package com.arifwidayana.notebookstore.data.local.model.request.store

data class UpdateItemRequest(
    val idItem: Int,
    val name: String?,
    val amountItem: Int?,
    val updatedAt: String?
)

package com.arifwidayana.notebookstore.data.local.model.request.store

data class InsertItemRequest(
    val name: String?,
    val amountItem: Int?,
    val supplier: String?,
    val createdAt: String?,
    val updatedAt: String?
)

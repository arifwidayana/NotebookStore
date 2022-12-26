package com.arifwidayana.notebookstore.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_table")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "name_item")
    var name: String?,
    @ColumnInfo(name = "amount_item")
    var amountItem: Int?,
    @ColumnInfo(name = "supplier_item")
    var supplier: String?,
    @ColumnInfo(name = "created_at")
    var createdAt: String?,
    @ColumnInfo(name = "updated_at")
    var updatedAt: String?
)

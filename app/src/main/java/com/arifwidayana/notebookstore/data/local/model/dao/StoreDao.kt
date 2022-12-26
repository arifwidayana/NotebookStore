package com.arifwidayana.notebookstore.data.local.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifwidayana.notebookstore.data.local.model.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(storeEntity: StoreEntity)

    @Query("SELECT * FROM store_table")
    fun getItem(): Flow<List<StoreEntity>>

    @Query("SELECT * FROM store_table WHERE id = :idItem")
    fun getItemById(idItem: Int): Flow<StoreEntity>

    @Query("DELETE FROM store_table WHERE id = :idItem")
    suspend fun deleteItemById(idItem: Int)

    @Query("UPDATE store_table " +
            "SET name_item = :name, amount_item = :amountItem, updated_at = :updatedAt " +
            "WHERE id = :idItem")
    suspend fun updateItemStore(
        idItem: Int,
        name: String?,
        amountItem: Int?,
        updatedAt: String?
    )
}
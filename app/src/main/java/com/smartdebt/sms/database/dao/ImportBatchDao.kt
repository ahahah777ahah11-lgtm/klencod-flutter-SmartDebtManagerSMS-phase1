package com.smartdebt.sms.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.smartdebt.sms.database.entity.ImportBatchEntity

@Dao
interface ImportBatchDao {
    @Insert
    suspend fun insert(batch: ImportBatchEntity): Long

    @Update
    suspend fun update(batch: ImportBatchEntity)

    @Query("SELECT * FROM import_batches ORDER BY importDate DESC")
    suspend fun all(): List<ImportBatchEntity>
}

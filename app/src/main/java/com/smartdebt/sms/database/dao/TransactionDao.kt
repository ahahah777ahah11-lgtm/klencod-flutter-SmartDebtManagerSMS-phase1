package com.smartdebt.sms.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smartdebt.sms.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity): Long

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE transactionType = :type")
    fun observeTotalByType(type: String): Flow<Long>

    @Query("SELECT * FROM transactions ORDER BY transactionDate DESC, id DESC LIMIT :limit")
    fun observeRecent(limit: Int): Flow<List<TransactionEntity>>
}

package com.smartdebt.sms.database.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.smartdebt.sms.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

/** Raw totals per customer. The final balance logic belongs to the Accounting Engine. */
data class CustomerWithTotals(
    @Embedded val customer: CustomerEntity,
    val totalDebt: Long,
    val totalPayments: Long
)

@Dao
interface CustomerDao {
    @Insert
    suspend fun insert(customer: CustomerEntity): Long

    @Update
    suspend fun update(customer: CustomerEntity)

    @Query(
        """
        SELECT c.*,
          COALESCE((SELECT SUM(amount) FROM transactions
                    WHERE customerId = c.id AND transactionType = 'DEBT'), 0) AS totalDebt,
          COALESCE((SELECT SUM(amount) FROM transactions
                    WHERE customerId = c.id AND transactionType = 'PAYMENT'), 0) AS totalPayments
        FROM customers c
        WHERE c.archived = 0
          AND (:query = '' OR c.name LIKE '%' || :query || '%'
               OR c.phone LIKE '%' || :query || '%'
               OR c.accountNumber LIKE '%' || :query || '%')
        ORDER BY c.name COLLATE NOCASE
        """
    )
    fun observeWithTotals(query: String): Flow<List<CustomerWithTotals>>

    @Query("SELECT COUNT(*) FROM customers WHERE archived = 0")
    fun observeActiveCount(): Flow<Int>
}

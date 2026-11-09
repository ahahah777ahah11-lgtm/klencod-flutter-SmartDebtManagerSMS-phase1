package com.smartdebt.sms.database

import androidx.room.withTransaction
import com.smartdebt.sms.database.dao.CustomerWithTotals
import com.smartdebt.sms.database.entity.AuditLogEntity
import com.smartdebt.sms.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val db: AppDatabase) {

    fun observeCustomers(query: String): Flow<List<CustomerWithTotals>> =
        db.customerDao().observeWithTotals(query.trim())

    fun observeActiveCount(): Flow<Int> = db.customerDao().observeActiveCount()

    /** Inserts the customer and its audit entry atomically. */
    suspend fun addCustomer(name: String, phone: String?, accountNumber: String?): Long =
        db.withTransaction {
            val id = db.customerDao().insert(
                CustomerEntity(
                    name = name.trim(),
                    phone = phone?.trim()?.ifEmpty { null },
                    accountNumber = accountNumber?.trim()?.ifEmpty { null }
                )
            )
            db.auditLogDao().insert(
                AuditLogEntity(
                    operationType = "CUSTOMER_ADDED",
                    relatedTable = "customers",
                    relatedRecordId = id,
                    newValue = name.trim()
                )
            )
            id
        }
}

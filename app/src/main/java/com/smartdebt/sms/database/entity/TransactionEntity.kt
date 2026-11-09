package com.smartdebt.sms.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("customerId"),
        Index("transactionDate"),
        Index("importBatchId"),
        // Original-record identity (nullable columns: manual entries never collide).
        Index(
            value = ["sourceApplication", "sourceTable", "sourceRecordId"],
            unique = true
        )
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sourceTransactionId: String? = null,
    val customerId: Long,
    /** Name of [com.smartdebt.sms.core.TransactionType]. */
    val transactionType: String,
    /** Positive minor units. Direction is defined by [transactionType]. */
    val amount: Long,
    val transactionDate: Long,
    val description: String? = null,
    val referenceNumber: String? = null,
    val invoiceNumber: String? = null,
    val paymentMethod: String? = null,
    val sourceApplication: String? = null,
    val sourceTable: String? = null,
    val sourceRecordId: String? = null,
    val importBatchId: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

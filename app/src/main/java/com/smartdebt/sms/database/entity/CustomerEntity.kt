package com.smartdebt.sms.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [Index("name"), Index("phone"), Index("accountNumber")]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sourceId: String? = null,
    val accountNumber: String? = null,
    val name: String,
    val phone: String? = null,
    val alternatePhone: String? = null,
    val address: String? = null,
    val company: String? = null,
    val notes: String? = null,
    val category: String? = null,
    val status: String = "ACTIVE",
    val sourceApplication: String? = null,
    val sourceDatabase: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastTransactionDate: Long? = null,
    val dueDate: Long? = null,
    // Archiving replaces deletion so financial history is never lost.
    val archived: Boolean = false
)

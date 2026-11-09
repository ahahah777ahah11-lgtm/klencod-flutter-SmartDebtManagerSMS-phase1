package com.smartdebt.sms.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "audit_log", indices = [Index("createdAt")])
data class AuditLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val operationType: String,
    val createdAt: Long = System.currentTimeMillis(),
    val relatedTable: String? = null,
    val relatedRecordId: Long? = null,
    val previousValue: String? = null,
    val newValue: String? = null,
    val source: String = "USER",
    val reason: String? = null
)

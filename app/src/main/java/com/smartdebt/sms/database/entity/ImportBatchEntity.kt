package com.smartdebt.sms.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "import_batches")
data class ImportBatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sourceFileName: String,
    val sourceApplication: String? = null,
    val importDate: Long = System.currentTimeMillis(),
    val sourceDatabaseHash: String? = null,
    val totalRecords: Int = 0,
    val importedRecords: Int = 0,
    val skippedRecords: Int = 0,
    val duplicateRecords: Int = 0,
    val errorRecords: Int = 0,
    /** SUCCESS, SUCCESS_WITH_WARNINGS, INCOMPLETE, FAILED, ROLLED_BACK */
    val importStatus: String = "INCOMPLETE",
    val mappingConfiguration: String? = null,
    val validationReport: String? = null,
    val userApproval: Boolean = false,
    val rollbackStatus: String? = null
)

package com.smartdebt.sms.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smartdebt.sms.database.dao.AuditLogDao
import com.smartdebt.sms.database.dao.CustomerDao
import com.smartdebt.sms.database.dao.ImportBatchDao
import com.smartdebt.sms.database.dao.TransactionDao
import com.smartdebt.sms.database.entity.AuditLogEntity
import com.smartdebt.sms.database.entity.CustomerEntity
import com.smartdebt.sms.database.entity.ImportBatchEntity
import com.smartdebt.sms.database.entity.TransactionEntity

@Database(
    entities = [
        CustomerEntity::class,
        TransactionEntity::class,
        ImportBatchEntity::class,
        AuditLogEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun importBatchDao(): ImportBatchDao
    abstract fun auditLogDao(): AuditLogDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smart_debt_manager.db"
                ).build().also { instance = it }
            }
    }
}

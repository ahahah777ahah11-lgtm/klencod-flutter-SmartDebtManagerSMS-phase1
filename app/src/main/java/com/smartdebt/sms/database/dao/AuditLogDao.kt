package com.smartdebt.sms.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smartdebt.sms.database.entity.AuditLogEntity

@Dao
interface AuditLogDao {
    @Insert
    suspend fun insert(entry: AuditLogEntity): Long

    @Query("SELECT * FROM audit_log ORDER BY createdAt DESC, id DESC LIMIT :limit")
    suspend fun latest(limit: Int): List<AuditLogEntity>
}

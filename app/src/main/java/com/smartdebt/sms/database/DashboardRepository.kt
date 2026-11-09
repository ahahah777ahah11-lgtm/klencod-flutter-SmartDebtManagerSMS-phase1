package com.smartdebt.sms.database

import com.smartdebt.sms.core.TransactionType
import kotlinx.coroutines.flow.Flow

class DashboardRepository(private val db: AppDatabase) {
    fun totalDebts(): Flow<Long> =
        db.transactionDao().observeTotalByType(TransactionType.DEBT.name)

    fun totalPayments(): Flow<Long> =
        db.transactionDao().observeTotalByType(TransactionType.PAYMENT.name)
}

package com.smartdebt.sms

import android.app.Application
import com.smartdebt.sms.database.AppDatabase
import com.smartdebt.sms.database.CustomerRepository
import com.smartdebt.sms.database.DashboardRepository

class SmartDebtApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.get(this) }
    val customerRepository: CustomerRepository by lazy { CustomerRepository(database) }
    val dashboardRepository: DashboardRepository by lazy { DashboardRepository(database) }
}

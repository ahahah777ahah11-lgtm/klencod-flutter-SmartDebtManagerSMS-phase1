package com.smartdebt.sms.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smartdebt.sms.SmartDebtApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DashboardState(
    val totalDebts: Long = 0,
    val totalPayments: Long = 0,
    val customerCount: Int = 0
)

class DashboardViewModel(app: Application) : AndroidViewModel(app) {
    private val application = app as SmartDebtApp

    // Every figure comes straight from the database, never from fixed numbers.
    val state: StateFlow<DashboardState> = combine(
        application.dashboardRepository.totalDebts(),
        application.dashboardRepository.totalPayments(),
        application.customerRepository.observeActiveCount()
    ) { debts, payments, count ->
        DashboardState(debts, payments, count)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DashboardState())
}

package com.smartdebt.sms.ui.customers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smartdebt.sms.SmartDebtApp
import com.smartdebt.sms.database.dao.CustomerWithTotals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CustomersViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as SmartDebtApp).customerRepository

    val query = MutableStateFlow("")

    val customers: StateFlow<List<CustomerWithTotals>> =
        query.flatMapLatest { repo.observeCustomers(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addCustomer(name: String, phone: String, accountNumber: String) {
        if (name.isBlank()) return
        viewModelScope.launch { repo.addCustomer(name, phone, accountNumber) }
    }
}

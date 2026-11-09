package com.smartdebt.sms.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smartdebt.sms.core.Money
import com.smartdebt.sms.ui.common.StatCard
import com.smartdebt.sms.ui.theme.DebtRed
import com.smartdebt.sms.ui.theme.PaidGreen

@Composable
fun DashboardScreen(vm: DashboardViewModel = viewModel()) {
    val state by vm.state.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "لوحة التحكم",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        StatCard("إجمالي الديون", Money.format(state.totalDebts), DebtRed)
        StatCard("إجمالي المدفوعات", Money.format(state.totalPayments), PaidGreen)
        StatCard(
            "عدد العملاء",
            state.customerCount.toString(),
            MaterialTheme.colorScheme.primary
        )
    }
}

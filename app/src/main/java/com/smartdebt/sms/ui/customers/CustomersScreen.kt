package com.smartdebt.sms.ui.customers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smartdebt.sms.core.Money
import com.smartdebt.sms.database.dao.CustomerWithTotals
import com.smartdebt.sms.ui.theme.DebtRed
import com.smartdebt.sms.ui.theme.PaidGreen

@Composable
fun CustomersScreen(vm: CustomersViewModel = viewModel()) {
    val customers by vm.customers.collectAsStateWithLifecycle()
    val query by vm.query.collectAsStateWithLifecycle()
    var showAdd by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            Text("العملاء", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Button(onClick = { showAdd = true }) { Text("إضافة عميل") }
        }

        OutlinedTextField(
            value = query,
            onValueChange = { vm.query.value = it },
            label = { Text("بحث بالاسم أو الهاتف أو رقم الحساب") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (customers.isEmpty()) {
            Text(
                if (query.isBlank()) "لا يوجد عملاء بعد. اضغط «إضافة عميل» للبدء."
                else "لا توجد نتائج مطابقة للبحث.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(customers, key = { it.customer.id }) { CustomerRow(it) }
            }
        }
    }

    if (showAdd) {
        AddCustomerDialog(
            onDismiss = { showAdd = false },
            onSave = { name, phone, account ->
                vm.addCustomer(name, phone, account)
                showAdd = false
            }
        )
    }
}

@Composable
private fun CustomerRow(item: CustomerWithTotals) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(item.customer.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            item.customer.phone?.let {
                Text(it, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("الديون: ${Money.format(item.totalDebt)}", color = DebtRed)
                Text("المدفوع: ${Money.format(item.totalPayments)}", color = PaidGreen)
            }
        }
    }
}

@Composable
private fun AddCustomerDialog(onDismiss: () -> Unit, onSave: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("إضافة عميل") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(name, { name = it }, label = { Text("اسم العميل") }, singleLine = true)
                OutlinedTextField(
                    phone, { phone = it }, label = { Text("رقم الهاتف") }, singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                OutlinedTextField(account, { account = it }, label = { Text("رقم الحساب (اختياري)") }, singleLine = true)
            }
        },
        confirmButton = {
            Button(onClick = { onSave(name, phone, account) }, enabled = name.isNotBlank()) { Text("حفظ") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } }
    )
}

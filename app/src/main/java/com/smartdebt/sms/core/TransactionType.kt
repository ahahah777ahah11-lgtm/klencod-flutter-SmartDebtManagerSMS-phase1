package com.smartdebt.sms.core

/**
 * Accounting meaning of each transaction type (see spec section 7.2).
 * Amounts are always stored as positive minor units; direction comes from the type.
 */
enum class TransactionType {
    DEBT,              // owed to the shop by the customer
    PAYMENT,           // paid by the customer
    CUSTOMER_CREDIT,   // owed to the customer by the shop
    OPENING_BALANCE,   // documented opening balance
    ADJUSTMENT,        // documented accounting adjustment
    REFUND,
    REVERSAL
}

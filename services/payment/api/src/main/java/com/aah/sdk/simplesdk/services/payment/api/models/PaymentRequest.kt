package com.aah.sdk.simplesdk.services.payment.api.models

data class PaymentRequest(
    val amount: Double,
    val transactionType: TransactionType,
)


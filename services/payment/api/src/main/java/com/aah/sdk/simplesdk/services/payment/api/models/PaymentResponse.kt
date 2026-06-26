package com.aah.sdk.simplesdk.services.payment.api.models

data class PaymentResponse(
    val originalRequest: PaymentRequest,
    val paymentStatus: PaymentStatus,
)
package com.aah.sdk.simplesdk.services.payment.api

import com.aah.sdk.simplesdk.services.payment.api.models.PaymentRequest
import com.aah.sdk.simplesdk.services.payment.api.models.PaymentResponse
import com.aah.sdk.simplesdk.services.payment.api.results.PaymentServiceResult


interface PaymentService {
    suspend fun pay(paymentRequest: PaymentRequest): PaymentServiceResult<PaymentResponse>
}
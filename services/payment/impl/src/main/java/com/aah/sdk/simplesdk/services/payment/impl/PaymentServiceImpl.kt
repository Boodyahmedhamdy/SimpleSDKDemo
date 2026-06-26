package com.aah.sdk.simplesdk.services.payment.impl

import android.util.Log
import com.aah.sdk.simplesdk.services.payment.api.PaymentService
import com.aah.sdk.simplesdk.services.payment.api.models.PaymentRequest
import com.aah.sdk.simplesdk.services.payment.api.models.PaymentResponse
import com.aah.sdk.simplesdk.services.payment.api.models.PaymentStatus
import com.aah.sdk.simplesdk.services.payment.api.results.PaymentServiceResult

private const val TAG = "PaymentServiceImpl"

class PaymentServiceImpl : PaymentService {
    override suspend fun pay(paymentRequest: PaymentRequest): PaymentServiceResult<PaymentResponse> {
        Log.i(TAG, "pay: Started payment request: $paymentRequest")
        return PaymentServiceResult.Success(
            data = PaymentResponse(
                originalRequest = paymentRequest,
                paymentStatus = PaymentStatus.SUCCESS
            )
        )
    }
}
package com.aah.sdk.simplesdk.services.payment.api.results

import com.aah.sdk.simplesdk.services.payment.api.exceptions.PaymentException

// 3. Sealed Interface for Result handling
sealed interface PaymentServiceResult<out T> {
    data class Success<out T>(val data: T) : PaymentServiceResult<T>
    data class Failure(val exception: PaymentException) : PaymentServiceResult<Nothing>
}
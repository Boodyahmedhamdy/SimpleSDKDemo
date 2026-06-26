package com.aah.sdk.simplesdk.services.payment.api.exceptions

import com.aah.sdk.simplesdk.services.payment.api.errors.PaymentErrorCode

// 2. Custom Payment Exceptions
sealed class PaymentException(
    val errorCode: PaymentErrorCode,
    message: String
) : Exception("[$ {errorCode.code}] $message") {

    class InsufficientFundsException : PaymentException(PaymentErrorCode.INSUFFICIENT_FUNDS, PaymentErrorCode.INSUFFICIENT_FUNDS.description)
    class CardExpiredException : PaymentException(PaymentErrorCode.CARD_EXPIRED, PaymentErrorCode.CARD_EXPIRED.description)
    class InvalidCvvException : PaymentException(PaymentErrorCode.INVALID_CVV, PaymentErrorCode.INVALID_CVV.description)
    class GatewayTimeoutException : PaymentException(PaymentErrorCode.NETWORK_TIMEOUT, PaymentErrorCode.NETWORK_TIMEOUT.description)
    class InternalServerErrorException : PaymentException(PaymentErrorCode.SERVER_ERROR, PaymentErrorCode.SERVER_ERROR.description)
    class GeneralPaymentException(customMessage: String? = null) : PaymentException(PaymentErrorCode.UNKNOWN_ERROR, customMessage ?: PaymentErrorCode.UNKNOWN_ERROR.description)
}
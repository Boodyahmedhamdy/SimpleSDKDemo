package com.aah.sdk.simplesdk.services.payment.api.errors

// 1. Error Codes Enum
enum class PaymentErrorCode(val code: Int, val description: String) {
    INSUFFICIENT_FUNDS(4001, "The account does not have enough funds to complete the transaction."),
    CARD_EXPIRED(4002, "The provided credit or debit card has expired."),
    INVALID_CVV(4003, "The card verification value (CVV) is incorrect."),
    NETWORK_TIMEOUT(5001, "The payment gateway timed out. Please try again."),
    SERVER_ERROR(5002, "An unexpected error occurred on the payment server."),
    UNKNOWN_ERROR(9999, "An unknown error has occurred.")
}
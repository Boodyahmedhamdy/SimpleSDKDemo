package com.aah.sdk.simplesdk

import com.aah.sdk.simplesdk.services.cardreader.api.CardReaderService
import com.aah.sdk.simplesdk.services.payment.api.PaymentService
import com.aah.sdk.simplesdk.services.printer.api.PrinterService

interface SimpleSDK {
    fun getPrinterService(): PrinterService?
    fun getCardReaderService(): CardReaderService?
    fun getPaymentService(): PaymentService?
}


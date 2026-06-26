package com.aah.sdk.simplesdk.provider

import com.aah.sdk.simplesdk.SimpleSDK
import com.aah.sdk.simplesdk.services.cardreader.api.CardReaderService

import com.aah.sdk.simplesdk.services.payment.impl.PaymentServiceImpl
import com.aah.sdk.simplesdk.services.printer.api.PrinterService


class SimpleSDKProvider : SimpleSDK {
    override fun getPrinterService(): PrinterService? {
        return PrinterServiceImpl()
    }

    override fun getCardReaderService(): CardReaderService? {
        return CardReaderServiceImpl()
    }


    override fun getPaymentService(): com.aah.sdk.simplesdk.services.payment.api.PaymentService {
        return PaymentServiceImpl()
    }
}
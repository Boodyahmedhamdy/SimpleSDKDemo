package com.aah.sdk.simplesdk.provider

import com.aah.sdk.simplesdk.SimpleSDK
import com.aah.sdk.simplesdk.services.cardreader.api.CardReaderService
import com.aah.sdk.simplesdk.services.cardreader.impl.CardReaderServiceImpl

import com.aah.sdk.simplesdk.services.payment.impl.PaymentServiceImpl
import com.aah.sdk.simplesdk.services.printer.api.PrinterService
import com.aah.sdk.simplesdk.services.printer.impl.PrinterServiceImpl


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
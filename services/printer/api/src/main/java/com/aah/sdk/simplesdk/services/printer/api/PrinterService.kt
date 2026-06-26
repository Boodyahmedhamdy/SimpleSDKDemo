package com.aah.sdk.simplesdk.services.printer.api

// 6. Service Definition
interface PrinterService {
    suspend fun printReceipt(printRequest: PrintRequest): PrinterResult<PrintReceiptResponse>
}


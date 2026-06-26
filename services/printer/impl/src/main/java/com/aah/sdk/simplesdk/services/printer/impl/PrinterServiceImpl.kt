package com.aah.sdk.simplesdk.services.printer.impl

import com.aah.sdk.simplesdk.services.printer.api.PrintReceiptResponse
import com.aah.sdk.simplesdk.services.printer.api.PrintRequest
import com.aah.sdk.simplesdk.services.printer.api.PrinterResult
import com.aah.sdk.simplesdk.services.printer.api.PrinterService

class PrinterServiceImpl : PrinterService {
    override suspend fun printReceipt(printRequest: PrintRequest): PrinterResult<PrintReceiptResponse> {
        // Mocking a successful hardware print operation
        return PrinterResult.Success(
            data = PrintReceiptResponse(
                transactionId = "TXN-987654321",
                printedLinesCount = printRequest.rawContent.lines().size + printRequest.feedLinesAtEnd,
                timestamp = System.currentTimeMillis(),
                hardwareSignature = byteArrayOf(0x0A, 0x0B, 0x0C)
            )
        )
    }
}
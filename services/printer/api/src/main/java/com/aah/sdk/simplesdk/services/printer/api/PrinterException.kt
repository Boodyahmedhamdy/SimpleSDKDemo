package com.aah.sdk.simplesdk.services.printer.api

// 2. Custom Printer Exceptions
sealed class PrinterException(
    val errorCode: PrinterErrorCode,
    message: String
) : Exception("[$ {errorCode.code}] $message") {

    class PrinterNotFoundException : PrinterException(
        PrinterErrorCode.PRINTER_NOT_FOUND,
        PrinterErrorCode.PRINTER_NOT_FOUND.description
    )

    class OutOfPaperException : PrinterException(
        PrinterErrorCode.OUT_OF_PAPER,
        PrinterErrorCode.OUT_OF_PAPER.description
    )

    class CoverOpenException : PrinterException(
        PrinterErrorCode.COVER_OPEN,
        PrinterErrorCode.COVER_OPEN.description
    )

    class PaperJamException : PrinterException(
        PrinterErrorCode.PAPER_JAM,
        PrinterErrorCode.PAPER_JAM.description
    )

    class OverheatedException : PrinterException(
        PrinterErrorCode.OVERHEATED,
        PrinterErrorCode.OVERHEATED.description
    )

    class LowBatteryException : PrinterException(
        PrinterErrorCode.LOW_BATTERY,
        PrinterErrorCode.LOW_BATTERY.description
    )

    class PrintFailedException : PrinterException(
        PrinterErrorCode.PRINT_FAILED,
        PrinterErrorCode.PRINT_FAILED.description
    )

    class GeneralPrinterException(customMessage: String? = null) : PrinterException(
        PrinterErrorCode.UNKNOWN_PRINTER_ERROR,
        customMessage ?: PrinterErrorCode.UNKNOWN_PRINTER_ERROR.description
    )
}
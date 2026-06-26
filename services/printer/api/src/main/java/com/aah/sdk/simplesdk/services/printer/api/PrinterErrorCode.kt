package com.aah.sdk.simplesdk.services.printer.api

// 1. Printer Error Codes Enum
enum class PrinterErrorCode(val code: Int, val description: String) {
    PRINTER_NOT_FOUND(3001, "The physical printer device is not connected or initialized."),
    OUT_OF_PAPER(3002, "The printer is out of paper."),
    COVER_OPEN(3003, "The printer paper roll cover is open."),
    PAPER_JAM(3004, "A paper jam occurred in the printer mechanism."),
    OVERHEATED(3005, "The printer head is overheated. Waiting for cool down."),
    LOW_BATTERY(3006, "The printer battery is too low to execute high-voltage printing."),
    PRINT_FAILED(3007, "The hardware command failed to transmit or execute completely."),
    UNKNOWN_PRINTER_ERROR(3999, "An unknown hardware or printer SDK error occurred.")
}
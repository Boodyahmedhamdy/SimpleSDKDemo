package com.aah.sdk.simplesdk.services.printer.api

// 3. Sealed Interface for Printer Result
sealed interface PrinterResult<out T> {
    data class Success<out T>(val data: T) : PrinterResult<T>
    data class Failure(val exception: PrinterException) : PrinterResult<Nothing>
}
package com.aah.sdk.simplesdk.services.printer.api

data class PrintRequest(
    val rawContent: String,        // ESC/POS commands or plaintext formatting
    val density: PrintDensity = PrintDensity.NORMAL,
    val feedLinesAtEnd: Int = 3
)
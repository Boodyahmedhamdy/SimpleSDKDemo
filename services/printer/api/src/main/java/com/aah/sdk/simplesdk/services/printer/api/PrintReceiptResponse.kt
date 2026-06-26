package com.aah.sdk.simplesdk.services.printer.api

// 4. Print Response Payload
data class PrintReceiptResponse(
    val transactionId: String,
    val printedLinesCount: Int,
    val timestamp: Long,
    val hardwareSignature: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PrintReceiptResponse

        if (transactionId != other.transactionId) return false
        if (printedLinesCount != other.printedLinesCount) return false
        if (timestamp != other.timestamp) return false
        if (!hardwareSignature.contentEquals(other.hardwareSignature)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = transactionId.hashCode()
        result = 31 * result + printedLinesCount
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + hardwareSignature.contentHashCode()
        return result
    }
}
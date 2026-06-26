package com.aah.sdk.simplesdk.services.cardreader.api


// 1. Card Reader Error Codes Enum
enum class CardReaderErrorCode(val code: Int, val description: String) {
    READER_NOT_FOUND(2001, "The physical card reader device is not connected or paired."),
    TIMEOUT(2002, "The card reading operation timed out before a card was presented."),
    INVALID_CARD(2003, "The inserted or tapped card could not be recognized (e.g., damaged chip)."),
    CARD_BLOCKED(2004, "The card is blocked due to too many incorrect PIN attempts."),
    CHIP_ERROR(
        2005,
        "The chip communication failed. Fallback to magnetic stripe or tap might be required."
    ),
    MSR_READ_ERROR(2006, "Failed to read the magnetic stripe properly."),
    TRANSACTION_CANCELED(2007, "The user or terminal canceled the card reading operation."),
    LOW_BATTERY(2008, "The bluetooth card reader battery is too low to perform a transaction."),
    UNKNOWN_READER_ERROR(2999, "An unknown hardware or SDK error occurred.")
}

// 2. Custom Card Reader Exceptions
sealed class CardReaderException(
    val errorCode: CardReaderErrorCode,
    message: String
) : Exception("[$ {errorCode.code}] $message") {

    class ReaderNotFoundException : CardReaderException(
        CardReaderErrorCode.READER_NOT_FOUND,
        CardReaderErrorCode.READER_NOT_FOUND.description
    )

    class ReaderTimeoutException :
        CardReaderException(CardReaderErrorCode.TIMEOUT, CardReaderErrorCode.TIMEOUT.description)

    class InvalidCardException : CardReaderException(
        CardReaderErrorCode.INVALID_CARD,
        CardReaderErrorCode.INVALID_CARD.description
    )

    class CardBlockedException : CardReaderException(
        CardReaderErrorCode.CARD_BLOCKED,
        CardReaderErrorCode.CARD_BLOCKED.description
    )

    class ChipErrorException : CardReaderException(
        CardReaderErrorCode.CHIP_ERROR,
        CardReaderErrorCode.CHIP_ERROR.description
    )

    class MsrReadErrorException : CardReaderException(
        CardReaderErrorCode.MSR_READ_ERROR,
        CardReaderErrorCode.MSR_READ_ERROR.description
    )

    class TransactionCanceledException : CardReaderException(
        CardReaderErrorCode.TRANSACTION_CANCELED,
        CardReaderErrorCode.TRANSACTION_CANCELED.description
    )

    class LowBatteryException : CardReaderException(
        CardReaderErrorCode.LOW_BATTERY,
        CardReaderErrorCode.LOW_BATTERY.description
    )

    class GeneralReaderException(customMessage: String? = null) : CardReaderException(
        CardReaderErrorCode.UNKNOWN_READER_ERROR,
        customMessage ?: CardReaderErrorCode.UNKNOWN_READER_ERROR.description
    )
}

// 3. Sealed Interface for Card Reader Result
sealed interface CardReaderResult<out T> {
    data class Success<out T>(val data: T) : CardReaderResult<T>
    data class Failure(val exception: CardReaderException) : CardReaderResult<Nothing>
}

data class CardData(
    val scheme: String,
    val pan: String,
    val track2: String,
    val binBlock: ByteArray,
    val cardHolderName: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardData

        if (scheme != other.scheme) return false
        if (pan != other.pan) return false
        if (track2 != other.track2) return false
        if (!binBlock.contentEquals(other.binBlock)) return false
        if (cardHolderName != other.cardHolderName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = scheme.hashCode()
        result = 31 * result + pan.hashCode()
        result = 31 * result + track2.hashCode()
        result = 31 * result + binBlock.contentHashCode()
        result = 31 * result + cardHolderName.hashCode()
        return result
    }

}

enum class CardSlot {
    CONTACT, CONTACTLESS, SWIPE, ALL
}

data class ReadCardRequest(
    val slots: Set<CardSlot>,
    val timeoutInSeconds: Int = 60
)

interface CardReaderService {
    suspend fun readCard(readingRequest: ReadCardRequest): CardReaderResult<CardData>
}


package com.aah.sdk.simplesdk.services.cardreader.impl

import com.aah.sdk.simplesdk.services.cardreader.api.CardData
import com.aah.sdk.simplesdk.services.cardreader.api.CardReaderResult
import com.aah.sdk.simplesdk.services.cardreader.api.CardReaderService
import com.aah.sdk.simplesdk.services.cardreader.api.ReadCardRequest

class CardReaderServiceImpl : CardReaderService {
    override suspend fun readCard(readingRequest: ReadCardRequest): CardReaderResult<CardData> {
        return CardReaderResult.Success(
            data = CardData(
                scheme = "VISA",
                pan = "1223213123123",
                track2 = "234234234-32423423",
                binBlock = byteArrayOf(),
                cardHolderName = "Ahmed"
            )
        )
    }
}
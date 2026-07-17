package com.aah.sdk.simplesdk.services.biometrics.api

enum class BiometricsErrorCode(val code: Int, val description: String) {
    OPERATION_FAILED(3001, "The Biometrics operation failed."),
    UNKNOWN_ERROR(3999, "An unknown error occurred.")
}

sealed class BiometricsException(
    val errorCode: BiometricsErrorCode,
    message: String
) : Exception("[${errorCode.code}] $message") {

    class OperationFailedException : BiometricsException(
        BiometricsErrorCode.OPERATION_FAILED,
        BiometricsErrorCode.OPERATION_FAILED.description
    )

    class GeneralException(customMessage: String? = null) : BiometricsException(
        BiometricsErrorCode.UNKNOWN_ERROR,
        customMessage ?: BiometricsErrorCode.UNKNOWN_ERROR.description
    )
}

sealed interface BiometricsResult<out T> {
    data class Success<out T>(val data: T) : BiometricsResult<T>
    data class Failure(val exception: BiometricsException) : BiometricsResult<Nothing>
}

interface BiometricsService {
    suspend fun performAction(): BiometricsResult<String>
}

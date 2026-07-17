package com.aah.sdk.simplesdk.services.biometrics.impl

import com.aah.sdk.simplesdk.services.biometrics.api.BiometricsResult
import com.aah.sdk.simplesdk.services.biometrics.api.BiometricsService

class BiometricsServiceImpl : BiometricsService {
    override suspend fun performAction(): BiometricsResult<String> {
        return BiometricsResult.Success("Success from BiometricsServiceImpl")
    }
}

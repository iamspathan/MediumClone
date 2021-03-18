package dev.iamspathan.realworldapi.models.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.Error

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val error: Error
)
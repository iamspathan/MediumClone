package dev.iamspathan.realworldapi.models.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.LoginData

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val user: LoginData
)
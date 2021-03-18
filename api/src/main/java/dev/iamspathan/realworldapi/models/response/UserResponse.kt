package dev.iamspathan.realworldapi.models.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.User

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "user")
    val user: User
)
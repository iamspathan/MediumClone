package dev.iamspathan.realworldapi.models.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.Comment

@JsonClass(generateAdapter = true)
data class CommentsResponse(
    @Json(name = "comment")
    val comments: List<Comment>
)
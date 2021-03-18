package dev.iamspathan.realworldapi.models.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.ArticleData

@JsonClass(generateAdapter = true)
data class ArticleRequest(
    @Json(name = "article")
    val article: ArticleData
)
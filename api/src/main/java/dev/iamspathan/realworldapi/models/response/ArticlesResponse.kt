package dev.iamspathan.realworldapi.models.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.iamspathan.realworldapi.models.entities.Article

@JsonClass(generateAdapter = true)
data class ArticlesResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "articlesCount")
    val articlesCount: Int
)
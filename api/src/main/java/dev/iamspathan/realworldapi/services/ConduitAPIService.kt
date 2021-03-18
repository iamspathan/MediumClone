package dev.iamspathan.realworldapi.services

import dev.iamspathan.realworldapi.models.request.LoginRequest
import dev.iamspathan.realworldapi.models.request.SignupRequest
import dev.iamspathan.realworldapi.models.response.ArticleResponse
import dev.iamspathan.realworldapi.models.response.ArticlesResponse
import dev.iamspathan.realworldapi.models.response.TagsResponse
import dev.iamspathan.realworldapi.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ConduitAPIService {

    @POST("users")
    suspend fun signupUser(
        @Body userCred: SignupRequest,
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCred: LoginRequest,
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null,
        @Query("tags") tags: String? = null,
    ): Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticlesBySlug(
        @Path("slug") slug: String,
    ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>
}
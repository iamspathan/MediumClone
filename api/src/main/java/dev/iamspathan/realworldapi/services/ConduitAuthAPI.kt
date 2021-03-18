package dev.iamspathan.realworldapi.services

import dev.iamspathan.realworldapi.models.entities.Article
import dev.iamspathan.realworldapi.models.request.ArticleRequest
import dev.iamspathan.realworldapi.models.request.UserUpdateRequest
import dev.iamspathan.realworldapi.models.response.ArticleResponse
import dev.iamspathan.realworldapi.models.response.ArticlesResponse
import dev.iamspathan.realworldapi.models.response.ProfileResponse
import dev.iamspathan.realworldapi.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ConduitAuthAPI {

    @GET("user")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body userUpdateRequest: UserUpdateRequest,
    ): Response<UserResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username: String,
    ): Response<ProfileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
        @Path("username") username: String,
    ): Response<ProfileResponse>

    @DELETE("profile/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username: String,
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug: String,
    ): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
        @Path("slug") slug: String,
    ): Response<ArticleResponse>

    @POST("articles")
    suspend fun createArticle(
        @Body articleRequest: ArticleRequest,
        ): Response<ArticleResponse>
}
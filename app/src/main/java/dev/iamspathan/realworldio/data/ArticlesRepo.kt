package dev.iamspathan.realworldio.data

import dev.iamspathan.realworldapi.ConduitClient

object ArticlesRepo {


    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun getGlobalFeed() = api.getArticles().body()?.articles


    suspend fun getMyFeed() = authAPI.getFeedArticles().body()?.articles


}
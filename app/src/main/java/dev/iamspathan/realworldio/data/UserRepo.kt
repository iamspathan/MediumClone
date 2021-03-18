package dev.iamspathan.realworldio.data

import dev.iamspathan.realworldapi.ConduitClient
import dev.iamspathan.realworldapi.models.entities.Article
import dev.iamspathan.realworldapi.models.entities.ArticleData
import dev.iamspathan.realworldapi.models.entities.LoginData
import dev.iamspathan.realworldapi.models.entities.SignupData
import dev.iamspathan.realworldapi.models.entities.User
import dev.iamspathan.realworldapi.models.entities.UserUpdateData
import dev.iamspathan.realworldapi.models.request.ArticleRequest
import dev.iamspathan.realworldapi.models.request.LoginRequest
import dev.iamspathan.realworldapi.models.request.SignupRequest
import dev.iamspathan.realworldapi.models.request.UserUpdateRequest
import dev.iamspathan.realworldapi.models.response.UserResponse
import retrofit2.Response

object UserRepo {

    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(user = LoginData(email, password)))
        //TODO: Save it in SharedPreference
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(userName: String, email: String, password: String): User? {
        val response = api.signupUser(SignupRequest(
            SignupData(
                email = email,
                password = password,
                username = userName
            )))

        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authAPI.getCurrentUser().body()?.user
    }

    suspend fun updateUser(
        bio: String?,
        email: String?,
        image: String?,
        userName: String?,
        password: String?,
    ): User? {
        val response = authAPI.updateCurrentUser(UserUpdateRequest(UserUpdateData(
            bio = bio,
            image = image,
            email = email,
            username = userName,
            password = password
        )))

        return response.body()?.user
    }

    suspend fun createArticle(
        articleTitle: String,
        articleAbout: String,
        articleBody: String,
        articleTags: List<String>?,
    ): Article? {

        val response = authAPI.createArticle(ArticleRequest(ArticleData(
            title = articleTitle,
            body = articleBody,
            description = articleAbout,
            tagList = articleTags
        ))).body()?.article

        return response
    }
}
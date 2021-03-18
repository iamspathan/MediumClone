package dev.iamspathan.realworldapi

import dev.iamspathan.realworldapi.models.request.SignupRequest
import dev.iamspathan.realworldapi.models.entities.SignupData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.*
import kotlin.random.Random

class ConduitClientTest {

    private val conduitClient = ConduitClient()


    @Test
    fun getArticles() {
        runBlocking {

        val articles = conduitClient.publicApi.getArticles()
        assertNotNull(articles.body()?.articles)
        }
    }



    @Test
    fun getArticlesByAuthor() {
        runBlocking {

            val articles = conduitClient.publicApi.getArticles(
                author = "aasdd"
            )
            assertNotNull(articles.body()?.articles)
        }
    }


    @Test
    fun getArticlesByTags() {
        runBlocking {

            val articles = conduitClient.publicApi.getArticles(
                tags = "dragons"
            )
            assertNotNull(articles.body()?.articles)
        }
    }



    @Test
    fun `post user signup user`(){
        runBlocking {

            val userCred = SignupData(
                email = "testemail${Random.nextInt(999,9999)}@test.com",
                password = "${Random.nextInt(999999,99999999)}",
                username = "randuser${Random.nextInt(999,9999)}"
            )
            val response = conduitClient.publicApi.signupUser(SignupRequest(userCred))

            assertEquals(userCred.username , response.body()?.user?.username)
        }


    }
}
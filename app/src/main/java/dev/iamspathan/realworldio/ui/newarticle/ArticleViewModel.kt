package dev.iamspathan.realworldio.ui.newarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iamspathan.realworldapi.models.entities.Article
import dev.iamspathan.realworldio.data.UserRepo
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {


    private val _article = MutableLiveData<Article>()

    val article : LiveData<Article> = _article


    fun createArticle( articleTitle: String,
        articleAbout: String,
        articleBody: String,
        articleTags: List<String>?) = viewModelScope.launch {
        UserRepo.createArticle(articleTitle, articleAbout, articleBody, articleTags).let {
            _article.postValue(it)
        }

    }





}
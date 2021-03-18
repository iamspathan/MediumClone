package dev.iamspathan.realworldio.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.iamspathan.realworldio.R
import dev.iamspathan.realworldio.databinding.FragmentArticleBinding
import dev.iamspathan.realworldio.extensions.loadImage
import dev.iamspathan.realworldio.extensions.timeStamp
import okhttp3.internal.http.hasBody

class ArticleFragment : Fragment() {

    private var _binding : FragmentArticleBinding?= null
    lateinit var articleViewModel: ArticleViewModel
    private var articleid : String ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)

        arguments?.let {
            articleid =  it.getString(resources.getString(R.string.arg_article_id))
        }

        articleid?.let { articleViewModel.fetchArticleBySlug(it) }

        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}){
            _binding?.apply {
                titleTextView.text = it.title
                descriptionTextView.text = it.body
                authorTextView.text = it.author.username
                dateTextView.timeStamp = it.createdAt
                avtarImageView.loadImage(it.author.image, true)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
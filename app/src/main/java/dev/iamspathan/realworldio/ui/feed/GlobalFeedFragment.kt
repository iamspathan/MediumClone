package dev.iamspathan.realworldio.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.iamspathan.realworldio.R
import dev.iamspathan.realworldio.databinding.FragmentFeedBinding

class GlobalFeedFragment : Fragment() {

    lateinit var binding: FragmentFeedBinding
    lateinit var viewmodel: FeedViewModel
    lateinit var articleAdapter: ArticleFeedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddArticle.setOnClickListener {
            findNavController().navigate(R.id.action_nav_feed_to_newArticleFragment)
        }

        viewmodel = ViewModelProvider(this).get(FeedViewModel::class.java)
        articleAdapter = ArticleFeedAdapter { openArticle(it) }

        binding.feedRecyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewmodel.fetchGlobalFeed()
        viewmodel.feed.observe(viewLifecycleOwner, Observer {
            articleAdapter.submitList(it)
        })
    }

    fun openArticle(articleId: String) {
        findNavController().navigate(R.id.actionGlobalfeed_openArticle,
            bundleOf(resources.getString(R.string.arg_article_id) to articleId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
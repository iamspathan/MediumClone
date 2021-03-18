package dev.iamspathan.realworldio.ui.newarticle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.iamspathan.realworldio.databinding.FragmentNewarticleBinding

class NewArticleFragment : Fragment() {

    private val TAG = "NewArticleFragment"
    private var binding: FragmentNewarticleBinding? = null
    lateinit var viewModel: ArticleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewarticleBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        Log.d(TAG, "onCreateView: INITIATED")

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            publishArticleButton.setOnClickListener {
                viewModel.createArticle(
                    articleTitleEditText.text.toString(),
                    articleAboutEditText.text.toString(),
                    articleBodyEditText.text.toString(),
                    listOf(articleTagsEditText.text.toString())
                )

                findNavController().popBackStack()
            }

        }

        viewModel.article.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Article ${it.title} is Published", Toast.LENGTH_SHORT).show()
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package dev.iamspathan.realworldio.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.iamspathan.realworldio.databinding.FragmentLoginSignupBinding
import dev.iamspathan.realworldio.databinding.FragmentSignupBinding

class SignupFragment  : Fragment() {

    private var _binding: FragmentLoginSignupBinding? = null
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        _binding?.usernameEditText?.isVisible = true

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            submitButton.setOnClickListener {
                viewModel.signup(
                    userName = usernameEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    password = passwordEditText.text.toString()
                )
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
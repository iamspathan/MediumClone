package dev.iamspathan.realworldio.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.iamspathan.realworldio.databinding.FragmentSettingsBinding
import dev.iamspathan.realworldio.ui.auth.AuthViewModel

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({ lifecycle }, Observer {

            _binding?.apply {
                bioEditText.setText(it?.bio)
                usernameEditText.setText(it?.username)
                imageEditText.setText(it?.image)
                emailEditText.setText(it?.email)
            }
        })


        _binding?.apply {

            submitProfileButton.setOnClickListener {

                authViewModel.update(
                    userName = usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    image = imageEditText.text.toString(),
                    password = passwordEditText.text.toString().takeIf { it.isNotBlank() },
                    email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                    bio = bioEditText.text.toString())

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
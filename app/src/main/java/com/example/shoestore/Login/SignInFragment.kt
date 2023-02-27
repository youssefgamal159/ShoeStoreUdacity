package com.example.shoestore.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentSignInBinding
class SignInFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentSignInBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_in, container, false)

        binding.signInButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToWelcomeFragment())
        }
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToWelcomeFragment())
        }
        return binding.root
    }
}
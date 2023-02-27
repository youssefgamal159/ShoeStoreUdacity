package com.example.shoestore.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        binding.welcomeButton.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        onBack()

        return binding.root
    }

    private fun onBack() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val intent = Intent()
                    intent.action = Intent.ACTION_MAIN
                    intent.addCategory(Intent.CATEGORY_HOME)
                    startActivity(intent)
                }
            })
    }
}
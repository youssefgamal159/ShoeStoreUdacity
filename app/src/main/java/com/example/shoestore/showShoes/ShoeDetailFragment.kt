package com.example.shoestore.showShoes

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestore.R
import com.example.shoestore.ShoeViewModel
import com.example.shoestore.ShoeViewModelFactory
import com.example.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeViewModel
    private lateinit var viewModelFactory : ShoeViewModelFactory
    private var fromImages : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        initViewModel()
        initObservers()
        showImage()

        binding.selectShoe.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesImagesFragment())
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearShoeTemplate()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.shoeViewModel = viewModel
        binding.shoe = viewModel.shoe
    }
     private fun showImage(){
        val intImage by navArgs<ShoeDetailFragmentArgs>()
        viewModelFactory = ShoeViewModelFactory()
        viewModel = ViewModelProvider (requireActivity() , viewModelFactory)
            .get(ShoeViewModel::class.java)
        if(intImage.intImage==0){
            binding.shoeImage.setImageResource(R.drawable.shoe1)
            fromImages=false
        }else{
            binding.shoeImage.setImageResource(intImage.intImage)
            viewModel.shoe.imageShoe = intImage.intImage
            fromImages = true
        }
    }
    private fun initObservers() {
        viewModel.eventSaveFailByNameCompanyShoeDetail.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                val message = "The name of the company is required"
                showAlert(message)
                viewModel.saveFailByNameCompanyShoeDetailComplete()
            }
        })
        viewModel.eventSaveFailByNameShoeDetail.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                val message = "The name of the shoe is required"
                showAlert(message)
                viewModel.saveFailByNameShoeDetailComplete()
            }
        })
        viewModel.eventSaveFailByNameCompanyShoeDetail.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                val message = "The name of the company is required"
                showAlert(message)
                viewModel.saveFailByNameCompanyShoeDetailComplete()
            }
        })
        viewModel.eventSaveFailBySizeShoeDetail.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                val message = "The size of the shoe is required and Shoe size should be greater than 35 and smaller than 46"
                showAlert(message)
                viewModel.saveFailBySizeShoeDetailComplete()
            }
        })
        viewModel.eventSaveShoeDetailPress.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                saveShoeDetail()
                viewModel.saveShoeDetailComplete()
            }
        })
        viewModel.eventCancelShoeDetailPress.observe(viewLifecycleOwner, Observer { event ->
            if(event){
                cancelShoeDetail()
                viewModel.cancelShoeDetailComplete()
            }
        })
    }
    private fun saveShoeDetail() {
        if(fromImages){
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
        }else{
            findNavController().popBackStack()
        }
    }
    private fun cancelShoeDetail() {
        if(fromImages){
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
        }else{
            findNavController().popBackStack()
        }
    }
    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context)
        with(builder)
        {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("OK", null)
            show()
        }
    }
}
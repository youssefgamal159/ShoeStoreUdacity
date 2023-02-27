package com.example.shoestore.showShoes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoestore.R
import com.example.shoestore.ShoeViewModel
import com.example.shoestore.adapter.ItemAdapter
import com.example.shoestore.databinding.FragmentShoesImagesBinding
import com.example.shoestore.model.ShoeImage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoesImagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ShoesImagesFragment : Fragment() {
    private lateinit var binding: FragmentShoesImagesBinding
    private lateinit var viewModel: ShoeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setBackPressedConfiguration()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_images, container, false)

        val list = mutableListOf<ShoeImage>()
        binding.recyclerViewList?.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewList?.setHasFixedSize(true)
        binding.recyclerViewList?.adapter = ItemAdapter(list)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        viewModel.getListElements().observe(requireActivity(), Observer {elementsSnapshot ->
            list.clear()
            list.addAll(elementsSnapshot)
            binding.recyclerViewList?.adapter?.notifyDataSetChanged()
        })
        var adapter = ItemAdapter(list)
        binding.recyclerViewList?.adapter = adapter
        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val action = ShoesImagesFragmentDirections.actionShoesImagesFragmentToShoeDetailFragment(position)
                NavHostFragment.findNavController(requireParentFragment()).navigate(action)
            }

        })



        return binding.root
    }



}
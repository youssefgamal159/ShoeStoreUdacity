package com.example.shoestore.showShoes

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.ShoeViewModel
import com.example.shoestore.ShoeViewModelFactory
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.databinding.ItemInListBinding
import com.example.shoestore.model.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel
    private lateinit var viewModelFactory : ShoeViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackPressedConfiguration()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        initViewModel()
        onBack()
        viewModel.listShoes.observe(viewLifecycleOwner, Observer { list ->
            itemToList(list)
        })
        binding.buttonList.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }
    private fun setBackPressedConfiguration() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val i = Intent()
                i.action = Intent.ACTION_MAIN
                i.addCategory(Intent.CATEGORY_HOME)
                startActivity(i)
            }
        })
    }
    private fun initViewModel() {
        viewModelFactory = ShoeViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ShoeViewModel::class.java)
    }
    private fun itemToList(listShoes: MutableList<Shoe>) {
        val parentLayout = binding.listShoes
        val item = 0
        for(item in item until listShoes.size step 1 ){
            val shoe = listShoes[item]
            val itemList: ItemInListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_in_list, parentLayout, false)
            itemList.nameItem.text = shoe.name
            itemList.companyItem.text = shoe.company
            itemList.sizeShoeList.text = shoe.size
            if(shoe.imageShoe==0){
                itemList.imageItem.setImageResource(R.drawable.shoe1)
            }else{
                itemList.imageItem.setImageResource(shoe.imageShoe)
            }
            parentLayout.addView(itemList.root)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_shoeListFragment_to_signInFragment)
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_logout,menu)
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
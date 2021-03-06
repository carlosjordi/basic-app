package com.carlosjordi.basicapp.ui.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.carlosjordi.basicapp.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemDetailBinding.inflate(inflater, container, false)

        val item = ItemDetailFragmentArgs.fromBundle(requireArguments()).item
        binding.item = item

        (activity as AppCompatActivity).supportActionBar?.title = item.title

        return binding.root
    }
}
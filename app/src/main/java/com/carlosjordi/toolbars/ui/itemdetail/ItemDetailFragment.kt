package com.carlosjordi.toolbars.ui.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.carlosjordi.toolbars.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemDetailBinding.inflate(inflater, container, false)

        val item = ItemDetailFragmentArgs.fromBundle(requireArguments()).item
        binding.item = item

        return binding.root
    }
}
package com.carlosjordi.toolbars.ui.itemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.carlosjordi.toolbars.R
import com.carlosjordi.toolbars.databinding.FragmentItemListBinding
import com.carlosjordi.toolbars.toolbar_1.ITEM_LIST
import com.carlosjordi.toolbars.toolbar_1.ItemAdapter
import com.carlosjordi.toolbars.toolbar_1.ItemClickListener

class ItemListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemListBinding.inflate(inflater, container, false)

        val adapter = ItemAdapter(ItemClickListener {
            // se ejecuta cuando se hace click en cualquiera de los items
            findNavController()
                .navigate(ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it))
        })
        binding.itemsList.adapter = adapter
        adapter.submitList(ITEM_LIST)

        return binding.root
    }
}

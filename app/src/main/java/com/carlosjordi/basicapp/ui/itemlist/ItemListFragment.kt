package com.carlosjordi.basicapp.ui.itemlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.carlosjordi.basicapp.R
import com.carlosjordi.basicapp.databinding.FragmentItemListBinding
import com.carlosjordi.basicapp.utils.ITEM_LIST

class ItemListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemListBinding.inflate(inflater, container, false)

        val adapter =
            ItemAdapter(ItemClickListener {
                // se ejecuta cuando se hace click en cualquiera de los items
                findNavController()
                    .navigate(ItemListFragmentDirections
                        .actionItemListFragmentToItemDetailFragment(it))
            })
        binding.itemsList.adapter = adapter
        adapter.submitList(ITEM_LIST)

        // navegando al fragmento donde agregaremos un item
        binding.fabAdd.setOnClickListener {
            findNavController()
                .navigate(ItemListFragmentDirections.actionItemListFragmentToAddItemFragment())
        }

        // indicamos que hay un menu
        setHasOptionsMenu(true)

        return binding.root
    }

    // indicamos que menu cargar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    // dejamos que el componente de navegación se encargue de redireccionarnos
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}

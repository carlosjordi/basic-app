package com.carlosjordi.toolbars.toolbar_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjordi.toolbars.databinding.ItemLayoutBinding

class ItemAdapter(val clickListener: ItemClickListener) :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, clickListener: ItemClickListener) {
            // hace el binding entre el item que se nos pase como parametro y el item
            // que tenemos en el layout, el cual se encargará de 'pintar' los datos en pantalla
            binding.item = item
            binding.listener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}

// clase de apoyo para que se recalcule la diferencia entre 2 items y hacer el menor
// esfuerzo para renderizar
class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}

// clase que se encargará del evento de click en nuestros items
class ItemClickListener(val clickListener: (item: Item) -> Unit) {
    fun onClick(item: Item) = clickListener(item)
}
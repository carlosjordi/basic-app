package com.carlosjordi.toolbars.toolbar_1

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.carlosjordi.toolbars.R

val ITEM_LIST = listOf(
    Item(
        1L,
        "Mueble empotrado",
        "Mueble empotrado con bonitos cuadros acompañandolo. Disponible a la venta",
        R.drawable._item_1
    ),
    Item(
        2L,
        "Sofa naranja",
        "Sofa de cuero naranja. Diseñado por Tulero Roski",
        R.drawable._item_2
    ),
    Item(
        3L,
        "Salón decorado",
        "Un par de estantes en conjunto a mueble y cuadro. Lámpara y arbolito incluidos. El Carrito no viene incluido.",
        R.drawable._item_3
    )
)

// esto nos ayudará a setear la imagen en el layout
@BindingAdapter("setImgResource")
fun ImageView.setImgResource(imgResource: Int) {
    this.setImageResource(imgResource)
}

@BindingAdapter("app:showCode")
fun TextView.setCode(item: Item) {
    // pequeña lógica para generar código de producto
    // basado en su ID + primera letra del producto +
    // primera letra de la descripcion
    val code = "${item.id}${item.title.substring(0, 1)}${item.description.substring(0, 1)}"
    this.text = this.context.getString(R.string.product_code, code)
}
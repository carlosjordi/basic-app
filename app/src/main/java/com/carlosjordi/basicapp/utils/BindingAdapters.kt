package com.carlosjordi.basicapp.utils

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.carlosjordi.basicapp.R
import com.carlosjordi.basicapp.entity.Item

// esto nos ayudará a setear la imagen en el layout
@BindingAdapter("setImgResource")
fun ImageView.setImgResource(imgResource: Any) {
    if (imgResource is Int) {
        this.setImageResource(imgResource)
    } else {
        this.setImageBitmap(imgResource as Bitmap)
    }
}

@BindingAdapter("app:showCode")
fun TextView.setCode(item: Item) {
    // pequeña lógica para generar código de producto
    // basado en su ID + primera letra del producto +
    // primera letra de la descripcion
    val code = "${item.id}${item.title.substring(0, 1)}${item.description.substring(0, 1)}"
    this.text = this.context.getString(R.string.product_code, code)
}
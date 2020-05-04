package com.carlosjordi.toolbars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.carlosjordi.toolbars.databinding.ActivityMainBinding
import com.carlosjordi.toolbars.toolbar_1.ItemAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val adapter = ItemAdapter()

        binding.itemsList.adapter = adapter
    }
}

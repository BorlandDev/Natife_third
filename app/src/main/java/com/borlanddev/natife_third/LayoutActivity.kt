package com.borlanddev.natife_third

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borlanddev.natife_third.databinding.ActivityLayoutBinding

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLayoutBinding.inflate(layoutInflater, CustomLayout(this))
        setContentView(binding.root)

        val customLayout = CustomLayout(this)
        binding.button.setOnClickListener {
                customLayout.addItem("TextView for test")
        }
    }
}
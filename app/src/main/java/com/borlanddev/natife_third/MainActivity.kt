package com.borlanddev.natife_third

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borlanddev.natife_third.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colors = listOf(
            Color.BLACK,
            Color.BLUE,
            Color.GRAY,
            Color.GREEN,
            Color.RED,
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.TRANSPARENT
        )

        binding.buttonChangeRadius.setOnClickListener {
            binding.customView.setRadius(Random.nextInt(20, 100))
        }

        binding.buttonChangeThickness.setOnClickListener {
            binding.customView.setThickness(Random.nextInt(20, 100))
        }

        binding.buttonChangeColor.setOnClickListener {
            binding.customView.setColor(
                colors[Random.nextInt(colors.size - 1)]
            )
        }
    }
}
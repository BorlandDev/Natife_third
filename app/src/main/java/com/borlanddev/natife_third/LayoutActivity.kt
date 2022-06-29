package com.borlanddev.natife_third

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        val customLayout = findViewById<CustomLayout>(R.id.customLayout)

        with(customLayout) {
            addItem("Тест")
            addItem("Тест1")
            addItem("Тест2")
            addItem("Тест3")
            addItem("Тест4")
        }
    }
}
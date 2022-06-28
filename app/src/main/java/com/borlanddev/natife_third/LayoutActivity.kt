package com.borlanddev.natife_third

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customLayout = CustomLayout(this)
        with(customLayout) {
            addItem("Тест")
            addItem("Тест1")
            addItem("Тест2")
            addItem("Тест3")
            addItem("Тест32вулатуцшщаругша2ушар2ушару2а2увыйв")
            addItem("Тест32вулатуцшщыфвыфвыфвыфв")
        }
    }
}
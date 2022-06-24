package com.borlanddev.natife_third

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.borlanddev.natife_third.databinding.LayoutCustomBinding

class CustomLayout @JvmOverloads constructor(
    context: Context,
    attrsLayout: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrsLayout, defStyle) {

    private val binding: LayoutCustomBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_custom, this, true)

        binding = LayoutCustomBinding.bind(this)
    }

    fun addItem(item: String) {
        val textView = TextView(context)
        textView.text = item
        addView(textView)
    }

}
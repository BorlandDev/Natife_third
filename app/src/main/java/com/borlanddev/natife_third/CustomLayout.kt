package com.borlanddev.natife_third

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.borlanddev.natife_third.databinding.ActivityLayoutBinding

class CustomLayout @JvmOverloads constructor(
    context: Context,
    attrsLayout: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrsLayout, defStyle) {

    private val binding: ActivityLayoutBinding
    private val params = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    private val margin = MarginLayoutParams(8, 8)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.activity_layout, this, true)
        binding = ActivityLayoutBinding.bind(this)

    }

    fun addItem(item: String) {
        val textView = TextView(context)
        with(textView) {
            layoutParams = params
            layoutParams = margin
            textSize = 18f
            setPadding(4, 4, 4, 4)
            setTextColor(resources.getColor(R.color.white))
            setBackgroundColor(resources.getColor(R.color.black))
        }
        addView(textView)
    }

}
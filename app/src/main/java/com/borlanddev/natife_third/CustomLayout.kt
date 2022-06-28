package com.borlanddev.natife_third

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomLayout @JvmOverloads constructor(
    context: Context,
    attrsLayout: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrsLayout, defStyle) {

    init {
        with(this) {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            orientation = VERTICAL
        }
    }

    fun addItem(item: String) {
        val textView = TextView(context)
        with(textView) {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            textSize = 18f
            text = item
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(4, 4, 4, 4)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }
        this.addView(textView)
    }
}
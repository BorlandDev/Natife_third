package com.borlanddev.natife_third

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom

class CustomLayout @JvmOverloads constructor(
    context: Context,
    attrsLayout: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrsLayout, defStyle) {

    init {
        with(this) {
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }

    fun addItem(item: String) {
        val textView = TextView(context)
        with(textView) {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            textSize = 18f
            text = item
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }
        this.addView(textView)
    }
}
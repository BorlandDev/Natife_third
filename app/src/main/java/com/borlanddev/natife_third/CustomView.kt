package com.borlanddev.natife_third

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class CustomView(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes) {

    private var roundingRadius by Delegates.notNull<Int>()
    private var thicknessLine by Delegates.notNull<Int>()
    private var colorLine by Delegates.notNull<Int>()
    private var rectPaint: Paint? = null
    private var rect = RectF(200f, 150f, 890f, 1800f)

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributesSet,
        defStyleAttr,
        R.style.DefaultCustomViewStyle
    )

    constructor(context: Context, attributesSet: AttributeSet?)
            : this(context, attributesSet, R.attr.customViewStyle)

    constructor(context: Context) : this(context, null)

    init {
        if (attributesSet != null) {
            initAttributes(attributesSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultAttributes()
        }
        initPaints()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectPaint?.let {
            canvas.drawRoundRect(
                rect,
                roundingRadius.toFloat(),
                roundingRadius.toFloat(),
                it
            )
        }
    }

    fun setRadius(value: Int) {
        roundingRadius = value
        invalidate()
    }

    fun setThickness(value: Int) {
        thicknessLine = value
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        rectPaint?.apply {
            style = Paint.Style.STROKE
            strokeWidth = value.toFloat()
            color = colorLine
        }
        invalidate()
    }

    fun setColor(value: Int) {
        colorLine = value
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        rectPaint?.apply {
            style = Paint.Style.STROKE
            strokeWidth = thicknessLine.toFloat()
            color = value
        }
        invalidate()
    }

    private fun initAttributes(
        attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) {
        val typedArray = context.obtainStyledAttributes(
            attributesSet,
            R.styleable.CustomView,
            defStyleAttr,
            defStyleRes
        )
        roundingRadius =
            typedArray.getInt(R.styleable.CustomView_rounding_radius, ROUNDING_RADIUS_DEF)
        thicknessLine =
            typedArray.getInt(R.styleable.CustomView_thickness_line, THICKNESS_LINE_DEF)
        colorLine = typedArray.getColor(R.styleable.CustomView_color_line, COLOR_LINE_DEF)

        typedArray.recycle()
    }

    private fun initDefaultAttributes() {
        roundingRadius = ROUNDING_RADIUS_DEF
        thicknessLine = THICKNESS_LINE_DEF
        colorLine = COLOR_LINE_DEF
    }

    private fun initPaints() {
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        rectPaint?.apply {
            style = Paint.Style.STROKE
            strokeWidth = thicknessLine.toFloat()
            color = colorLine
        }
    }

    companion object {
        const val ROUNDING_RADIUS_DEF = 50
        const val THICKNESS_LINE_DEF = 30
        const val COLOR_LINE_DEF = Color.GRAY
    }

}
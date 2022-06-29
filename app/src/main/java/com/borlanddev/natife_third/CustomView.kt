package com.borlanddev.natife_third

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var roundingRadius: Float = ROUNDING_RADIUS_DEF
    private var thicknessLine: Float = THICKNESS_LINE_DEF
    private var colorLine: Int = COLOR_LINE_DEF
    private val rectPaint: Paint
        get() = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = thicknessLine
        color = colorLine
    }
    private val rect = RectF(0f, 0f, 0f, 0f)
    private var rectPadding: Float = 0f
    set(value) {
       field = value
       //requestLayout()
    }


    init {
        initAttributes(attrs)
        initPaints()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(
            rect,
            roundingRadius,
            roundingRadius,
            rectPaint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val resultWidth = if (widthMode == MeasureSpec.EXACTLY) width else WIDTH_DEF
        val resultHeight = if (heightMode == MeasureSpec.EXACTLY) height else HEIGHT_DEF

        rect.set(
            paddingStart.toFloat() + rectPadding,
            paddingTop.toFloat() + rectPadding,
            resultWidth.toFloat() - paddingEnd - rectPadding,
            resultHeight.toFloat() - paddingBottom - rectPadding
        )

        setMeasuredDimension(
            resultWidth.toInt(),
            resultHeight.toInt()
        )
    }

    fun setRadius(value: Float) {
        roundingRadius = value
        invalidate()
    }

    fun setThickness(value: Float) {
        thicknessLine = value
        rectPaint.strokeWidth = value
        rectPadding = value * 0.5f
        invalidate()
    }

    fun setColor(value: Int) {
        colorLine = value
        rectPaint.color = value
        invalidate()
    }

    private fun initAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0, 0
        ).apply {
            try {
                roundingRadius =
                    getDimension(R.styleable.CustomView_rounding_radius, ROUNDING_RADIUS_DEF)
                thicknessLine =
                    getDimension(R.styleable.CustomView_thickness_line, THICKNESS_LINE_DEF)
                colorLine = getColor(R.styleable.CustomView_color_line, COLOR_LINE_DEF)

                rectPadding = thicknessLine * 0.5f
            } finally {
                recycle()
            }
        }
    }

    private fun initPaints() {
        rectPaint.apply {
            strokeWidth = thicknessLine
            color = colorLine
        }
    }

    companion object {
        const val ROUNDING_RADIUS_DEF = 50f
        const val THICKNESS_LINE_DEF = 100f
        const val COLOR_LINE_DEF = Color.GRAY
        const val WIDTH_DEF = 400f
        const val HEIGHT_DEF = 800f
    }
}
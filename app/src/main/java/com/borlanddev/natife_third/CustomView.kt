package com.borlanddev.natife_third

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var roundingRadius: Float = 0f
    private var thicknessLine: Float = 0f
    private var colorLine: Int = 0
    private var rectPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = THICKNESS_LINE_DEF
        color = COLOR_LINE_DEF
    }
    private val rect = RectF(0f, 0f, 0f, 0f)

    init {
        initAttributes(attrs)
        initPaints()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectPaint.let {
            canvas.drawRoundRect(
                rect,
                roundingRadius,
                roundingRadius,
                it
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var resultWidth = if (widthMode == MeasureSpec.EXACTLY) width else WIDTH_DEF
        resultWidth =
            if (widthMode == MeasureSpec.AT_MOST) min(width, resultWidth.toInt()) else WIDTH_DEF

        var resultHeight = if (heightMode == MeasureSpec.EXACTLY) width else HEIGHT_DEF
        resultHeight =
            if (heightMode == MeasureSpec.AT_MOST) min(height, resultHeight.toInt()) else HEIGHT_DEF

        rect.set(
            0f + paddingStart,
            0f + paddingTop,
            resultWidth.toFloat() - paddingEnd,
            resultHeight.toFloat() - paddingBottom
        )
        setMeasuredDimension(resultWidth.toInt(), resultHeight.toInt())
    }

    fun setRadius(value: Float) {
        roundingRadius = value
        invalidate()
    }

    fun setThickness(value: Float) {
        thicknessLine = value
        rectPaint.strokeWidth = value
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
        const val THICKNESS_LINE_DEF = 30f
        const val COLOR_LINE_DEF = Color.GRAY
        const val WIDTH_DEF = 1000f
        const val HEIGHT_DEF = 1800f
    }

}
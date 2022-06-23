package com.borlanddev.natife_third

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.lang.Integer.min

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
    private val rect = RectF(0f,0f,0f,0f)

    init {
        if (attrs != null) {
            initAttributes(attrs)
        }
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
    // договариваемся с компоновщиком о наших размерах , он хочет измерять нашу вью
    // вьюха должна сопоставить свои предполагаемые размеры с размерами которые предоставляет компоновщик
    // и в финале уже сообщить - какого размера она будет

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        //val measuredWidth = rect.width() + paddingLeft + paddingRight
        //val measuredHeight = rect.height() + paddingTop + paddingBottom

        val resultWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> width
            //MeasureSpec.AT_MOST -> min(width, measuredWidth.toInt())
            else -> WIDTH_DEF
        }
        val resultHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> height
            //MeasureSpec.AT_MOST -> min(height, measuredHeight.toInt())
            else -> HEIGHT_DEF
        }
        /* if (resultHeight.toFloat() < measuredHeight || resultWidth.toFloat() < measuredWidth) {
             Log.e(CustomView::class.java.name,
                 "View's height is smaller than it's required to draw the content. It might be cropped"    )}*/

        rect.set(0f,0f,resultWidth.toFloat(),resultHeight.toFloat())

        setMeasuredDimension(resultWidth.toInt() + paddingStart + paddingEnd,
            resultHeight.toInt() + paddingTop + paddingBottom)
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
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        rectPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = thicknessLine
            color = colorLine
        }
    }

    companion object {
        const val ROUNDING_RADIUS_DEF = 50f
        const val THICKNESS_LINE_DEF = 30f
        const val COLOR_LINE_DEF = Color.GRAY
        const val WIDTH_DEF = 400f
        const val HEIGHT_DEF = 1000f
    }

}
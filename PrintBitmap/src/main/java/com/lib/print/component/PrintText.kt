package com.lib.print.component

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint


/**
 * Created by Anonim date on 23/02/2023.
 * Bengkulu, Indonesia.
 * Copyright (c) Company. All rights reserved.
 **/
open class PrintText(protected val text: String, protected val fontSize: Int, align: Align = Align.LEFT, val isBold: Boolean = false) : BasePrint(align) {

    protected val padding = 8
    protected val paint = TextPaint()

    constructor(text: String,fontSize: FontSize = FontSize.NORMAL, align: Align = Align.LEFT, isBold: Boolean = false) : this(text, fontSize.size, align, isBold)

    override fun height(): Int {
        val bound = Rect()
        paint.textSize = fontSize.toFloat()
        paint.isFakeBoldText = isBold
        paint.getTextBounds(text, 0, text.length, bound)

        return bound.height() + padding
    }

    override fun draw(canvas: Canvas, vector: Vector) {
        val paint = TextPaint()
        paint.isFakeBoldText = isBold
        val dx: Int

        when(align) {
            Align.CENTER -> {
                dx = vector.x + (vector.width / 2)
                paint.textAlign = Paint.Align.CENTER
            }
            Align.RIGHT -> {
                dx = vector.width
                paint.textAlign = Paint.Align.RIGHT
            }
            else -> {
                dx = vector.x
                paint.textAlign = Paint.Align.LEFT
            }
        }

        paint.textSize = fontSize.toFloat()

        canvas.drawText(text, dx.toFloat(), vector.y.toFloat(), paint)
    }
}

enum class FontSize(val size: Int) {
    SMALL(16),  NORMAL(20), LARGE(30)
}
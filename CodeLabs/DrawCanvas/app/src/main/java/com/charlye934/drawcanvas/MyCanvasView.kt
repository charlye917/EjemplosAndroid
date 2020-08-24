package com.charlye934.drawcanvas

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

class MyCanvasView(context:Context): View(context) {

    private lateinit var extraCanvas: Canvas
    private var extraBitmap: Bitmap? = null
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawing = Path()
    private val curtPath = Path()

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDHT
    }
    private var path = Path()

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private var currentX = 0f
    private var curretnY = 0f

    private val touchTolerance = ViewConfiguration.get(context).scaledPagingTouchSlop

    private lateinit var frame:Rect


    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap!!)
        extraCanvas.drawColor(backgroundColor)

        if (extraBitmap != null && extraBitmap!!.isRecycled){
            extraBitmap!!.recycle()
            extraBitmap = null
        }

        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(extraBitmap!!, 0f, 0f, null)
        canvas?.drawRect(frame, paint)

        canvas!!.drawPath(drawing, paint)
        canvas.drawPath(curtPath, paint)
        canvas.drawRect(frame, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        motionTouchEventX = event!!.x
        motionTouchEventY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart(){
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        curretnY = motionTouchEventY
    }

    private fun touchMove(){
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - curretnY)

        if(dx >= touchTolerance || dy >= touchTolerance){
            path.quadTo(currentX, curretnY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + curretnY) / 2)
            currentX = motionTouchEventX
            curretnY = motionTouchEventY
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp(){
        //Reset the path so it doesn't get draw againt
        path.reset()
    }

    companion object {
        private const val STROKE_WIDHT = 12f
    }
}
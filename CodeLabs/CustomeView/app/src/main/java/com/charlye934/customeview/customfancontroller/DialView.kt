package com.charlye934.customeview.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.charlye934.customeview.R
import java.lang.Float.min
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label: Int){
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when(this){
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0.0f//Radius of the circle
    private var fanSpeed = FanSpeed.OFF//The active selection
    //Position variable which will be used to draw be label and indicator cicle position
    private val pointPosition: Point = Point(0, 0)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSpeedMaxColor = 0

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.DialView){
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSpeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
        }
    }

    override fun performClick(): Boolean {
        if(super.performClick()) return true

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)

        invalidate()
        return true
    }

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            radius = (min(width, height) / 2.0 * 0.8).toFloat()
        }
    }

    private fun Point.computerXYForSpeed(pos: FanSpeed, radius: Float){
        val starAngle = Math.PI * (9 / 8.0)
        val angle = starAngle + pos.ordinal * (Math.PI / 4)
        x = ((radius * cos(angle)).toFloat() + width / 2).toInt()
        y = ((radius * sin(angle)).toFloat() + height / 2).toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //Set dial background color to green if selection not off
        paint.color = when(fanSpeed){
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSpeedMaxColor
        }
        //Draw the dial
        canvas!!.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        //draw the indicator circle
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computerXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x.toFloat(), pointPosition.y.toFloat(), radius/12, paint)
        //Draw the text labels
        val labelRdius = radius + RADIUS_OFFSET_LABEL
        for(i in FanSpeed.values()){
            pointPosition.computerXYForSpeed(i, labelRdius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x.toFloat(), pointPosition.y.toFloat(), paint)
        }
    }

}
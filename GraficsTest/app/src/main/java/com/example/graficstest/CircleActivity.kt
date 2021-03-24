package com.example.graficstest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class CircleActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {


    val parties = arrayOf(
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        title = "PieChartActivity"

        seekBarX.setOnSeekBarChangeListener(this)
        seekBarY.setOnSeekBarChangeListener(this)

        chart.setUsePercentValues(true)
        chart.description.isEnabled = false
        chart.setExtraOffsets(5f, 10f, 5f, 5f)
        chart.dragDecelerationFrictionCoef = 0.95f
        //chart.setCenterTextTypeface(tfLight)
        chart.centerText = generateCenterSpannableText()
        chart.isDrawHoleEnabled = true
        chart.setHoleColor(Color.WHITE)
        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(110)
        chart.holeRadius = 58f
        chart.transparentCircleRadius = 61f
        chart.setDrawCenterText(true)
        chart.rotationAngle = 0f
        // enable rotation of the chart by touch
        chart.isRotationEnabled = true
        chart.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this)
        seekBarX.progress = 4
        seekBarY.progress = 10
        chart.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);
        val l = chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE)
        //chart.setEntryLabelTypeface(tfRegular)
        chart.setEntryLabelTextSize(12f)
    }

    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range + range / 5).toFloat(),
                    //parties.get(i % parties.size),
                    resources.getDrawable(R.drawable.star)))
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        for (c: Int in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        //data.setValueTypeface(tfLight)
        chart!!.data = data

        // undo all highlights
        chart!!.highlightValues(null)
        chart!!.invalidate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.viewGithub -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/PieChartActivity.java")
                startActivity(i)
            }
            R.id.actionToggleValues -> {
                for (set: IDataSet<*> in chart!!.data.dataSets) set.setDrawValues(!set.isDrawValuesEnabled)
                chart!!.invalidate()
            }
            R.id.actionToggleIcons -> {
                for (set: IDataSet<*> in chart!!.data.dataSets) set.setDrawIcons(!set.isDrawIconsEnabled)
                chart!!.invalidate()
            }
            R.id.actionToggleHole -> {
                chart!!.isDrawHoleEnabled = !chart!!.isDrawHoleEnabled
                chart!!.invalidate()
            }
            R.id.actionToggleMinAngles -> {
                if (chart!!.minAngleForSlices == 0f) chart!!.minAngleForSlices = 36f else chart!!.minAngleForSlices = 0f
                chart!!.notifyDataSetChanged()
                chart!!.invalidate()
            }
            R.id.actionToggleCurvedSlices -> {
                val toSet = !chart!!.isDrawRoundedSlicesEnabled || !chart!!.isDrawHoleEnabled
                chart!!.setDrawRoundedSlices(toSet)
                if (toSet && !chart!!.isDrawHoleEnabled) {
                    chart!!.isDrawHoleEnabled = true
                }
                if (toSet && chart!!.isDrawSlicesUnderHoleEnabled) {
                    chart!!.setDrawSlicesUnderHole(false)
                }
                chart!!.invalidate()
            }
            R.id.actionDrawCenter -> {
                if (chart!!.isDrawCenterTextEnabled) chart!!.setDrawCenterText(false) else chart!!.setDrawCenterText(true)
                chart!!.invalidate()
            }
            R.id.actionToggleXValues -> {
                chart!!.setDrawEntryLabels(!chart!!.isDrawEntryLabelsEnabled)
                chart!!.invalidate()
            }
            R.id.actionTogglePercent -> {
                chart!!.setUsePercentValues(!chart!!.isUsePercentValuesEnabled)
                chart!!.invalidate()
            }
            R.id.animateX -> {
                chart!!.animateX(1400)
            }
            R.id.animateY -> {
                chart!!.animateY(1400)
            }
            R.id.animateXY -> {
                chart!!.animateXY(1400, 1400)
            }
            R.id.actionToggleSpin -> {
                chart!!.spin(1000, chart!!.rotationAngle, chart!!.rotationAngle + 360, Easing.EaseInOutCubic)
            }
        }
        return true
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        tvXMax!!.text = seekBarX!!.progress.toString()
        tvYMax.text = seekBarY.progress.toString()
        setData(seekBarX!!.progress, seekBarY.progress.toFloat())
    }

    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }

    override fun onValueSelected(e: Entry?, h: Highlight) {
        if (e == null) return
        Log.i("VAL SELECTED",
                "Value: " + e.y + ", index: " + h.x
                        + ", DataSet index: " + h.dataSetIndex)
    }

    override fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}
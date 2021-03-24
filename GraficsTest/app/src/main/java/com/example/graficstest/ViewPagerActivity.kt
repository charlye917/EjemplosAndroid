package com.example.graficstest

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private lateinit var colors: List<Int>
    private lateinit var models: ArrayList<Model>
    private val argEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        models = ArrayList()
        models.add(
            Model(
                R.drawable.brochure,
                "Brochure",
                "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"
            )
        )
        models.add(
            Model(
                R.drawable.sticker,
                "Sticker",
                "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"
            )
        )
        models.add(
            Model(
                R.drawable.poster,
                "Poster",
                "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."
            )
        )
        models.add(
            Model(
                R.drawable.namecard,
                "Namecard",
                "Business cards are cards bearing business information about a company or individual."
            )
        )

        adapter = Adapter(models, this)

        viewPager.adapter = adapter
        viewPager.setPadding(130, 0, 130, 0)

        val colors_temp = arrayOf(
            resources.getColor(R.color.color1),
            resources.getColor(R.color.color2),
            resources.getColor(R.color.color3),
            resources.getColor(R.color.color4)
        )

        colors = colors_temp.toList()

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position < adapter.count - 1 && position < colors.size - 1) {
                    viewPager.setBackgroundColor(
                        (argEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                        ) as Int)
                    )
                } else {
                    viewPager.setBackgroundColor(colors[colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
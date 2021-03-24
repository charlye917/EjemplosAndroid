package com.example.graficstest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.item.view.*

class Adapter(val models: List<Model>, val context: Context) : PagerAdapter() {

    override fun getCount(): Int = models.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item, container, false)

        view.image.setImageResource(models[position].image)
        view.title.text = models[position].title
        view.desc.text = models[position].desc

        view.setOnClickListener {
            Toast.makeText(context, "Click en ${view.title.text}", Toast.LENGTH_SHORT).show()
        }

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}
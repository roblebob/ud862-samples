package com.example.android.tickcross

import android.app.Activity
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.android.tickcross.R

class MainActivity : Activity() {
    private var tickCross: ImageView? = null
    private var tickToCross: AnimatedVectorDrawable? = null
    private var crossToTick: AnimatedVectorDrawable? = null
    private var tick = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tickCross = findViewById<View>(R.id.tick_cross) as ImageView
        tickToCross = getDrawable(R.drawable.avd_tick_to_cross) as AnimatedVectorDrawable?
        crossToTick = getDrawable(R.drawable.avd_cross_to_tick) as AnimatedVectorDrawable?
    }

    fun animate(view: View?) {
        val drawable = if (tick) tickToCross else crossToTick
        tickCross!!.setImageDrawable(drawable)
        drawable!!.start()
        tick = !tick
    }
}
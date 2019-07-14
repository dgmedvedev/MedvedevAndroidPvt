package by.itacademy.pvt.dz8

import android.app.Activity
import android.util.DisplayMetrics

class ScreenSizeCalculator {
    private val metrics = DisplayMetrics()

    fun findSize(activity: Activity): Double {
        activity.windowManager.defaultDisplay.getMetrics(metrics)

        val yInches = metrics.heightPixels / metrics.ydpi
        val xInches = metrics.widthPixels / metrics.xdpi

        return Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
    }
}
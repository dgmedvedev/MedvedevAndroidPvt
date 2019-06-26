package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class MyViewDz5 : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f

/*
    var sizeArray: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    private var arrays = IntArray(sizeArray)
    constructor(context: Context?, array: IntArray) : super(context) {
        this.arrays = array
    }
    */

    private var myArray = intArrayOf(25, 30, 60)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )
}
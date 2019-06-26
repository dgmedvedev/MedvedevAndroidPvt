package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R

class MyViewDz5 : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    private var cx = 0f
    private var cy = 0f
    private var cxLine = 0f
    private var cyLine = 0f
    private var cxLine1 = 0f
    private var cyLine1 = 0f
    private var radius = 0f
    private var myArray = intArrayOf(1, 2, 2)
    private var arrayAngle = FloatArray(myArray.size)

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

    init {
        arrayAngle = Methods.foundAngles(myArray)

        paint.color = ContextCompat.getColor(context, R.color.colorPrimary)
    }

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheiht: Int) {
        super.onSizeChanged(width, height, oldwidth, oldheiht)

        cx = width / 2f
        cy = height / 2f
        radius = Math.min(width, height) / 2f
        cxLine = (radius * Math.sin(Math.toRadians(arrayAngle[0].toDouble()))).toFloat() + cx
        cyLine = (cy - radius * Math.cos(Math.toRadians(arrayAngle[0].toDouble()))).toFloat()
        cxLine1 = (radius * Math.sin(Math.toRadians(arrayAngle[1].toDouble()))).toFloat() + cx
        cyLine1 = (cy - radius * Math.cos(Math.toRadians(arrayAngle[1].toDouble()))).toFloat()
    }

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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        path.moveTo(cx, cy)
        path.lineTo(cxLine, cyLine)
        path.lineTo(cx, cy - radius)
        path.close()

        canvas.drawPath(path, paint)
        canvas.rotate(arrayAngle[0])

        path.moveTo(cx, cy)
        path.lineTo(cxLine1, cyLine1)
        path.lineTo(cx, cy - radius)
        path.close()

        canvas.drawPath(path, paint)
        canvas.rotate(arrayAngle[1])
    }
}
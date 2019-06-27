package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R
import java.util.Random

class MyViewDz5 : View {

    private val paintSector = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintCircleOnLine = Paint(Paint.ANTI_ALIAS_FLAG)
    private var random = Random()

    private val rectF = RectF()

    private var cx = 0f
    private var cy = 0f
    private var cxLineStart = 0f
    private var cyLineStart = 0f
    private var cxLineEnd = 0f
    private var cyLineEnd = 0f

    private var radius = 0f
    private var radiusCircleOnLine = 0f
    private var startAngle = 0f
    private var centreAngle = 0f

    private var myArray = intArrayOf(10, 50, 40)
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
        arrayAngle = findAngles(myArray)
        paintLine.color = ContextCompat.getColor(context, R.color.colorPrimary)
        paintText.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        paintText.textSize = resources.getDimension(R.dimen.textSector)
        paintCircleOnLine.color = ContextCompat.getColor(context, R.color.colorPrimary)
    }

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheiht: Int) {
        super.onSizeChanged(width, height, oldwidth, oldheiht)

        cx = width / 2f
        cy = height / 2f
        radius = Math.min(width, height) / 4f
        radiusCircleOnLine = radius * 0.05f
        paintLine.strokeWidth = radiusCircleOnLine * 0.2f
        val paddingLeftRight = (width - 2 * radius) / 2
        val paddingTopBottom = (height - 2 * radius) / 2

        rectF.left = paddingLeftRight
        rectF.top = paddingTopBottom
        rectF.right = width - paddingLeftRight
        rectF.bottom = height - paddingTopBottom
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

        for (i in arrayAngle.indices) {
            centreAngle = arrayAngle[i] / 2f + startAngle
            paintSector.color = Color.argb(
                255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256)
            )
            canvas.drawArc(rectF, startAngle, arrayAngle[i], true, paintSector)
            cxLineStart = (radius * Math.cos(Math.toRadians((centreAngle).toDouble()))).toFloat() + cx
            cyLineStart = (cy + radius * Math.sin(Math.toRadians((centreAngle).toDouble()))).toFloat()
            cxLineEnd = (radius * 1.2f * Math.cos(Math.toRadians((centreAngle).toDouble()))).toFloat() + cx
            cyLineEnd = (cy + radius * 1.2f * Math.sin(Math.toRadians((centreAngle).toDouble()))).toFloat()
            canvas.drawLine(cxLineStart, cyLineStart, cxLineEnd, cyLineEnd, paintLine)
            canvas.drawCircle(cxLineEnd, cyLineEnd, radiusCircleOnLine, paintCircleOnLine)
            startAngle += arrayAngle[i]

            if (centreAngle > 90 && centreAngle < 270) {
                if (centreAngle > 180)
                    canvas.drawText(myArray[i].toString(), cxLineEnd - 50, cyLineEnd - 20, paintText)
                else canvas.drawText(myArray[i].toString(), cxLineEnd - 50, cyLineEnd + 50, paintText)
            } else {
                if (centreAngle <= 90)
                    canvas.drawText(myArray[i].toString(), cxLineEnd + 10, cyLineEnd + 50, paintText)
                else canvas.drawText(myArray[i].toString(), cxLineEnd + 10, cyLineEnd - 20, paintText)
            }
        }
    }

    private fun findAngles(array: IntArray): FloatArray {
        var sumArray = 0f
        val sizeArray = array.size
        val arrayAngles = FloatArray(sizeArray)

        for (angle in array) {
            sumArray += angle
        }
        for (i in array.indices) {
            val angle = array[i] * 360 / sumArray
            arrayAngles[i] = angle
        }
        return arrayAngles
    }
}
package by.itacademy.pvt.dz5.part1

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

class Dz5MyView : View {

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

    private val angle90 = 90f
    private val angle180 = 180f
    private val angle270 = 270f

    private val percent5 = 0.05f
    private val percent20 = 0.20f
    private val percent120 = 1.2f

    private val alphaRGB = 255
    private val of256RGB = 256

    private val padding5dp = resources.getDimension(R.dimen.padding5dp)
    private val padding10dp = resources.getDimension(R.dimen.padding10dp)
    private val padding20dp = resources.getDimension(R.dimen.padding20dp)
    private val padding25dp = resources.getDimension(R.dimen.padding25dp)

    var sizeArray = 0
        set(value) {
            field = value
            invalidate()
        }

    var myArray: IntArray = IntArray(sizeArray)
        set(value) {
            field = value
            invalidate()
        }

    private var arrayAngle = FloatArray(sizeArray)

    init {
        paintLine.color = ContextCompat.getColor(context, R.color.colorPrimary)
        paintText.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        paintText.textSize = resources.getDimension(R.dimen.textSector)
        paintCircleOnLine.color = ContextCompat.getColor(context, R.color.colorPrimary)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheiht: Int) {
        super.onSizeChanged(width, height, oldwidth, oldheiht)

        cx = width / 2f
        cy = height / 2f
        radius = Math.min(width, height) / 4f
        radiusCircleOnLine = radius * percent5
        paintLine.strokeWidth = radiusCircleOnLine * percent20
        val paddingLeftRight = (width - 2f * radius) / 2f
        val paddingTopBottom = (height - 2f * radius) / 2f

        rectF.left = paddingLeftRight
        rectF.top = paddingTopBottom
        rectF.right = width - paddingLeftRight
        rectF.bottom = height - paddingTopBottom

        arrayAngle = findAngles(myArray)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        for (i in arrayAngle.indices) {
            centreAngle = arrayAngle[i] / 2f + startAngle
            paintSector.color = Color.argb(
                alphaRGB, random.nextInt(of256RGB),
                random.nextInt(of256RGB), random.nextInt(of256RGB)
            )
            canvas.drawArc(rectF, startAngle, arrayAngle[i], true, paintSector)
            cxLineStart = (radius * Math.cos(Math.toRadians((centreAngle).toDouble()))).toFloat() + cx
            cyLineStart = (cy + radius * Math.sin(Math.toRadians((centreAngle).toDouble()))).toFloat()
            cxLineEnd = (radius * percent120 * Math.cos(Math.toRadians((centreAngle).toDouble()))).toFloat() + cx
            cyLineEnd = (cy + radius * percent120 * Math.sin(Math.toRadians((centreAngle).toDouble()))).toFloat()
            canvas.drawLine(cxLineStart, cyLineStart, cxLineEnd, cyLineEnd, paintLine)
            canvas.drawCircle(cxLineEnd, cyLineEnd, radiusCircleOnLine, paintCircleOnLine)
            startAngle += arrayAngle[i]

            if (centreAngle > angle90 && centreAngle < angle270) {
                if (centreAngle > angle180)
                    canvas.drawText(
                        myArray[i].toString(),
                        cxLineEnd - padding20dp, cyLineEnd - padding10dp, paintText
                    )
                else canvas.drawText(
                    myArray[i].toString(),
                    cxLineEnd - padding20dp, cyLineEnd + padding25dp, paintText
                )
            } else {
                if (centreAngle <= angle90)
                    canvas.drawText(
                        myArray[i].toString(),
                        cxLineEnd + padding5dp, cyLineEnd + padding25dp, paintText
                    )
                else canvas.drawText(
                    myArray[i].toString(),
                    cxLineEnd + padding5dp, cyLineEnd - padding10dp, paintText
                )
            }
        }
    }

    private fun findAngles(array: IntArray): FloatArray {
        var sumArray = 0f
        val angle360 = 360f
        val sizeArray = array.size
        val arrayAngles = FloatArray(sizeArray)

        for (angle in array) {
            sumArray += angle
        }
        for (i in array.indices) {
            val angle = array[i] * angle360 / sumArray
            arrayAngles[i] = angle
        }
        return arrayAngles
    }
}
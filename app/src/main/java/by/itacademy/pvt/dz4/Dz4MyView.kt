package by.itacademy.pvt.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R

class Dz4MyView : View {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleClockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cx = 0f
    private var cx3 = 0f
    private var cx6 = 0f
    private var cx9 = 0f
    private var cx12 = 0f
    private var cy = 0f
    private var cy3 = 0f
    private var cy6 = 0f
    private var cy9 = 0f
    private var cy12 = 0f
    private var radius = 0f
    private var radiusClock = 0f

    private var number3: String = ""
    private var number6: String = ""
    private var number9: String = ""
    private var number12: String = ""

    var text: String? = null
        set(value) {
            field = value
            invalidate()
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

    init {
        circlePaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        circleClockPaint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        numberPaint.color = ContextCompat.getColor(context, R.color.colorAccent)

        number3 = "3"
        number6 = "6"
        number9 = "9"
        number12 = "12"
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val center = Math.min(width, height) / 2f
        val paddingLeftRight = width*0.10f

        radius = 25f
        radiusClock = center - paddingLeftRight
        cx = width / 2f
        
        cy = height / 2f
        cx3 = radiusClock
        cy3 = radiusClock + 30
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        canvas.drawCircle(cx,cy,radiusClock,circleClockPaint)
        canvas.drawCircle(cx,cy,radius,circlePaint)

        canvas.drawText(number3,cx3,cy3,numberPaint)
        canvas.drawText(number6,cx6,cy6,numberPaint)
        canvas.drawText(number9,cx9,cy9,numberPaint)
        canvas.drawText(number12,cx12,cy12,numberPaint)
    }
}
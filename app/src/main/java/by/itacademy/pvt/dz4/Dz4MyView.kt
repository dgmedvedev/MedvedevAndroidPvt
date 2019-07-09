package by.itacademy.pvt.dz4

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
import java.util.Date

class Dz4MyView : View {

    private val circleSmallPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleClockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val pathArrowH = Path()
    private val pathArrowM = Path()

    private val date = Date()

    private val rotateArrowsSecAndMin = 6f
    private val rotateArrowHour = 30f

    private var numberSize = 0f
    private var textSize = 0f
    private var widthArrowHour = 0f
    private var edgeArrowHour = 0f
    private var lengthArrowHour = 0f
    private var widthArrowMinute = 0f
    private var edgeArrowMinute = 0f
    private var lengthArrowMinute = 0f
    private var lengthArrowSecond = 0f
    private var lengthPointerHour = 0f

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
    private var cxText = 0f
    private var cyText = 0f
    private var radiusSmallCircle = 0f
    private var radiusClock = 0f
    private var cyCentreLine = 0f

    private val number3: String = "3"
    private val number6: String = "6"
    private val number9: String = "9"
    private val number12: String = "12"
    private val nameClock: String = resources.getString(R.string.name_clock)

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
        circleSmallPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        circleClockPaint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        linePaint.color = ContextCompat.getColor(context, R.color.AustriaRed)
        arrowSecondPaint.color = ContextCompat.getColor(context, R.color.AustriaRed)
        numberPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        arrowPaint.color = ContextCompat.getColor(context, R.color.menu)
        textPaint.color = ContextCompat.getColor(context, R.color.white)

        linePaint.strokeWidth = 7f
        arrowSecondPaint.strokeWidth = 3f

        numberSize = resources.getDimension(R.dimen.number_size)
        textSize = resources.getDimension(R.dimen.text_size)

        numberPaint.textSize = numberSize
        textPaint.textSize = textSize

        widthArrowHour = resources.getDimension(R.dimen.width_arrow_hour)
        edgeArrowHour = resources.getDimension(R.dimen.edge_arrow_hour)
        lengthArrowHour = resources.getDimension(R.dimen.length_arrow_hour)
        widthArrowMinute = resources.getDimension(R.dimen.width_arrow_minute)
        edgeArrowMinute = resources.getDimension(R.dimen.edge_arrow_minute)
        lengthArrowMinute = resources.getDimension(R.dimen.length_arrow_minute)
        lengthArrowSecond = resources.getDimension(R.dimen.length_arrow_second)

        lengthPointerHour = resources.getDimension(R.dimen.length_pointer_hour)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val paddingLeftRight = if (width <= height) {
            width * 0.20f
        } else height * 0.20f

        radiusSmallCircle = resources.getDimension(R.dimen.radius_small_circle)
        radiusClock = Math.min(width, height) / 2f - paddingLeftRight

        cx = width / 2f
        cy = height / 2f
        cyCentreLine = height / 2f - radiusClock

        cx3 = width / 2f + radiusClock + numberSize / 2f
        cy3 = height / 2f + numberSize / 2f
        cx6 = width / 2f - numberSize / 4f
        cy6 = height / 2f + radiusClock + numberSize * 1.2f
        cx9 = width / 2f - radiusClock - numberSize
        cy9 = height / 2f + numberSize / 2f
        cx12 = width / 2f - numberSize / 2f
        cy12 = height / 2f - radiusClock - numberSize / 2.2f
        cxText = width / 2f - radiusClock / 3f
        cyText = height / 2f - radiusClock / 2f

        pathArrowH.moveTo(cx, cy)
        pathArrowH.lineTo(cx + widthArrowHour, cy - radiusClock + edgeArrowHour)
        pathArrowH.lineTo(cx, cy - radiusClock + lengthArrowHour)
        pathArrowH.lineTo(cx - widthArrowHour, cy - radiusClock + edgeArrowHour)
        pathArrowH.close()

        pathArrowM.moveTo(cx, cy)
        pathArrowM.lineTo(cx + widthArrowMinute, cy - radiusClock + edgeArrowMinute)
        pathArrowM.lineTo(cx, cy - radiusClock + lengthArrowMinute)
        pathArrowM.lineTo(cx - widthArrowMinute, cy - radiusClock + edgeArrowMinute)
        pathArrowM.close()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        canvas.drawCircle(cx, cy, radiusClock, circleClockPaint)
        canvas.drawCircle(cx, cy, radiusSmallCircle, circleSmallPaint)

        for (i in 1..12) {
            canvas.drawLine(
                cx, cyCentreLine + lengthPointerHour,
                cx, cyCentreLine - lengthPointerHour, linePaint
            )
            canvas.rotate(rotateArrowHour, cx, cy)
        }

        canvas.drawText(number3, cx3, cy3, numberPaint)
        canvas.drawText(number6, cx6, cy6, numberPaint)
        canvas.drawText(number9, cx9, cy9, numberPaint)
        canvas.drawText(number12, cx12, cy12, numberPaint)
        canvas.drawText(nameClock, cxText, cyText, textPaint)

        canvas.rotate(date.hours * rotateArrowHour, cx, cy)
        canvas.drawPath(pathArrowH, arrowPaint)
        canvas.rotate(-date.hours * rotateArrowHour, cx, cy)

        canvas.rotate(date.minutes * rotateArrowsSecAndMin, cx, cy)
        canvas.drawPath(pathArrowM, arrowPaint)
        canvas.rotate(-date.minutes * rotateArrowsSecAndMin, cx, cy)

        canvas.rotate(date.seconds * rotateArrowsSecAndMin, cx, cy)
        canvas.drawLine(
            cx, cy + lengthArrowSecond,
            cx, cyCentreLine + lengthArrowSecond, arrowSecondPaint
        )
    }
}
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

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleClockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val pathArrowH = Path()
    private val pathArrowM = Path()

    private val date = Date()

    private val numberSize = resources.getDimension(R.dimen.number_size)
    private val textSize = resources.getDimension(R.dimen.text_size)

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
    private var radius = 0f
    private var radiusClock = 0f
    private var cyCentreLine = 0f

    private var number3: String = ""
    private var number6: String = ""
    private var number9: String = ""
    private var number12: String = ""
    private var text: String = ""

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
        linePaint.color = ContextCompat.getColor(context, R.color.AustriaRed)
        arrowSecondPaint.color = ContextCompat.getColor(context, R.color.AustriaRed)
        numberPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        arrowPaint.color = ContextCompat.getColor(context, R.color.menu)
        textPaint.color = ContextCompat.getColor(context, R.color.white)

        linePaint.strokeWidth = 7f
        arrowSecondPaint.strokeWidth = 3f

        numberPaint.textSize = numberSize
        textPaint.textSize = textSize

        number3 = "3"
        number6 = "6"
        number9 = "9"
        number12 = "12"
        text = "TISSOT"
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val paddingLeftRight = if (width <= height) {
            width * 0.20f
        } else height * 0.20f

        radius = 15f
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
        pathArrowH.lineTo(cx + 25, cy - radiusClock + 100)
        pathArrowH.lineTo(cx, cy - radiusClock + 75)
        pathArrowH.lineTo(cx - 25, cy - radiusClock + 100)
        pathArrowH.close()

        pathArrowM.moveTo(cx, cy)
        pathArrowM.lineTo(cx + 15, cy - radiusClock + 40)
        pathArrowM.lineTo(cx, cy - radiusClock + 20)
        pathArrowM.lineTo(cx - 15, cy - radiusClock + 40)
        pathArrowM.close()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        canvas.drawCircle(cx, cy, radiusClock, circleClockPaint)
        canvas.drawCircle(cx, cy, radius, circlePaint)

        for (i in 1..12) {
            canvas.drawLine(cx, cyCentreLine + 20, cx, cyCentreLine - 20, linePaint)
            canvas.rotate(30f, cx, cy)
        }

        canvas.drawText(number3, cx3, cy3, numberPaint)
        canvas.drawText(number6, cx6, cy6, numberPaint)
        canvas.drawText(number9, cx9, cy9, numberPaint)
        canvas.drawText(number12, cx12, cy12, numberPaint)
        canvas.drawText(text, cxText, cyText, textPaint)

        canvas.rotate(date.hours * 30f, cx, cy)
        canvas.drawPath(pathArrowH, arrowPaint)
        canvas.rotate(-date.hours * 30f, cx, cy)

        canvas.rotate(date.minutes * 6f, cx, cy)
        canvas.drawPath(pathArrowM, arrowPaint)
        canvas.rotate(-date.minutes * 6f, cx, cy)

        canvas.rotate(date.seconds * 6f, cx, cy)
        canvas.drawLine(cx, cy + 20, cx, cyCentreLine + 30, arrowSecondPaint)
    }
}
package dev.ebnbin.ebdev

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.GestureDetectorCompat
import dev.ebnbin.eb.layoutInflater
import dev.ebnbin.ebdev.databinding.EbdevDevFloatingViewBinding
import kotlin.math.roundToInt

/**
 * Dev 悬浮按钮.
 */
internal class DevFloatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    init {
        EbdevDevFloatingViewBinding.inflate(this.context.layoutInflater, this)
    }

    //*****************************************************************************************************************

    private val gestureDetector: GestureDetectorCompat = GestureDetectorCompat(this.context, this)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        requireNotNull(event)
        if (gestureDetector.onTouchEvent(event)) return true
        return super.onTouchEvent(event)
    }

    //*****************************************************************************************************************

    private lateinit var downRawOffset: Pair<Float, Float>

    override fun onDown(e: MotionEvent?): Boolean {
        requireNotNull(e)
        val locationOnScreen = IntArray(2).also {
            getLocationOnScreen(it)
        }
        downRawOffset = locationOnScreen[0] - e.rawX to locationOnScreen[1] - e.rawY
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        requireNotNull(e2)
        val x = (downRawOffset.first + e2.rawX).roundToInt()
        val y = (downRawOffset.second + e2.rawY).roundToInt()
        listener?.onScroll(this, x, y)
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        val listener = listener ?: return
        if (listener.onLongPress(this)) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    //*****************************************************************************************************************

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        val listener = listener ?: return false
        if (listener.onSingleTap(this)) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        val listener = listener ?: return false
        if (listener.onDoubleTap(this)) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    //*****************************************************************************************************************

    var listener: Listener? = null

    interface Listener {
        fun onScroll(view: View, x: Int, y: Int) = Unit

        /**
         * @return 是否震动.
         */
        fun onSingleTap(view: View): Boolean = false

        /**
         * @return 是否震动.
         */
        fun onDoubleTap(view: View): Boolean = false

        /**
         * @return 是否震动.
         */
        fun onLongPress(view: View): Boolean = false
    }
}

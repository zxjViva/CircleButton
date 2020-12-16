package com.zxj.circlebutton

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout


class CircleButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var rotateAnimation: ObjectAnimator? = null
    var scaleAnimation: AnimatorSet? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.cricle_button, this)
    }

    fun startRotate() {
        post {
            rotateAnimation = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f).apply {
                repeatCount = -1
                duration = 1000
                target = this@CircleButton
                start()
            }
        }
    }

    fun stopRotate() {
        rotateAnimation?.cancel()
    }

    fun startScale() {
        scaleAnimation?.cancel()
        val bigX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.2f)
        val bigY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.2f)
        val smallX = ObjectAnimator.ofFloat(this, "scaleX", 1.2f, 1f)
        val smallY = ObjectAnimator.ofFloat(this, "scaleY", 1.2f, 1f)
        scaleAnimation = AnimatorSet().apply {
            setTarget(this@CircleButton)
            duration = 1000
            play(bigX).with(bigY)
            play(smallX).with(smallY).after(bigX)
        }
        scaleAnimation?.start()
    }

    override fun performClick(): Boolean {
        startScale()
        return super.performClick()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP && isScaling()){
            return false
        }
        return super.onTouchEvent(event)
    }

    private fun isScaling():Boolean{
        return (scaleAnimation?.isStarted() == true && scaleAnimation?.isRunning() == true)
    }
}
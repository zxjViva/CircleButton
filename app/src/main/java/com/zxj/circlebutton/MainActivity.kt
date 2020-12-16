package com.zxj.circlebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<CircleButton>(R.id.cb).apply {
            startRotate()
            setOnClickListener {
            }
        }
        handler.postDelayed({
            findViewById<CircleButton>(R.id.cb).performClick()
        },5000)
    }
}
package com.jonduran.floatinglockbutton

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.content.BroadcastReceiver
import android.content.Context
import android.widget.ImageButton
import android.view.WindowManager
import android.view.ViewGroup
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.MotionEvent
import android.util.Log
import android.app.admin.DevicePolicyManager

/**
 * Created by jonathanduran on 1/29/15.
 */
open class FloatingButtonService : Service() {

    var windowManager: WindowManager? = null
    var floatingButton: ImageButton? = null

    inner class FloatingButtonReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            floatingButton?.performClick()
        }
    }

    var receiver: BroadcastReceiver = FloatingButtonReceiver()

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(Context.WINDOW_SERVICE) as? WindowManager

        floatingButton = ImageButton(this)
        floatingButton?.setLayoutParams(ViewGroup.LayoutParams(48, 48))
        floatingButton?.setBackgroundResource(R.drawable.circle_button)
        // TODO: add image to the button

        val params: WindowManager.LayoutParams = WindowManager.LayoutParams(
                150, 150, WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.RIGHT
        params.x = 0
        params.y = 100

        windowManager?.addView(floatingButton, params)

        floatingButton?.setOnTouchListener { (view, motionEvent) ->
            var initialX: Int = 0
            var initialY: Int = 0
            var initialTouchX: Float = 0.0f
            var initialTouchY: Float = 0.0f
            var moved: Boolean = false

            when (motionEvent.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    moved = false
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = motionEvent.getRawX()
                    initialTouchY = motionEvent.getRawY()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    if(!moved)
                        floatingButton?.performClick()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    moved = true
                    params.x = initialX - (motionEvent.getRawX() - initialTouchX) as Int
                    params.y = initialY - (motionEvent.getRawY() - initialTouchY) as Int
                    windowManager?.updateViewLayout(floatingButton, params)
                    true
                }
                else -> {
                    false
                }
            }
        }

        floatingButton?.setOnClickListener { lockScreen() }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        windowManager?.removeView(floatingButton)
    }

    private fun lockScreen() {
        var devicePolicyManager: DevicePolicyManager =
                getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        devicePolicyManager.lockNow()
    }
}
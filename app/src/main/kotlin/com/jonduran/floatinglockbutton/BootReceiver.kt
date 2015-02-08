package com.jonduran.floatinglockbutton

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

/**
 * Created by jonathanduran on 2/7/15.
 */
open class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var preferences: SharedPreferences = context.getSharedPreferences(MainActivity2.PREF, Context.MODE_PRIVATE)

        var serviceEnabled: Boolean = preferences.getBoolean(MainActivity2.BUTTON_DISPLAYED, false)

        if (serviceEnabled) {
            var floatingService: Intent = Intent(context, FloatingButtonService.getClass())
            context.startService(floatingService)
        }
    }
}
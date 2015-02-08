package com.jonduran.floatinglockbutton

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by jonathanduran on 2/7/15.
 */
open class Admin : DeviceAdminReceiver() {
    fun showToast(context: Context?, msg: CharSequence?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG)
    }

    override fun onEnabled(context: Context?, intent: Intent?) {
        showToast(context, "Floating Lock Button: enabled")
    }

    override fun onDisabled(context: Context?, intent: Intent?) {
        showToast(context, "Floating Lock Button: disabled")
    }
}
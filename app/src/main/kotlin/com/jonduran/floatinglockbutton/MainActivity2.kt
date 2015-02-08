package com.jonduran.floatinglockbutton

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.text.SpannableString
import android.text.Spannable
import android.text.Spanned

/**
 * Created by jonathanduran on 1/28/15.
 */
open class MainActivity2 : ActionBarActivity() {
    final val RESULT_ENABLE: Int = 1

    class object {
        public final val PREF: String = "floating_lock_button_pref"
        public final val ADMIN_ENABLED: String = "admin_enabled"
        public final val BUTTON_DISPLAYED: String = "button_displayed"
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.getItemId()

        if(id == R.id.action_settings)
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun styleActionBar() {
        var title: SpannableString = SpannableString(getString(R.string.app_name))
        title.setSpan(TypefaceSpan(this, "RobotoCondensed-Bold.ttf"), 0, title.length(),
                Spanned.SPAN_POINT_MARK)
        getSupportActionBar().setTitle(title)
    }
}
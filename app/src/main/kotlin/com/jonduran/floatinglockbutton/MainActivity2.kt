package com.jonduran.floatinglockbutton

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

/**
 * Created by jonathanduran on 1/28/15.
 */
open class MainActivity2 : ActionBarActivity() {
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

}
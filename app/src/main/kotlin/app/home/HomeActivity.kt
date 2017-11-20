package app.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import app.settings.SettingsActivity
import kotlinx.android.synthetic.main.include_toolbar.*
import org.stoyicker.dinger.R

internal class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> true.also { startActivity(SettingsActivity.getCallingIntent(this)) }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}

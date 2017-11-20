package app.settings

import android.app.startIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.include_toolbar.*
import org.stoyicker.dinger.R

internal class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu) = super.onCreateOptionsMenu(menu).also {
        menuInflater.inflate(R.menu.activity_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_linkedin -> true.also { startIntent(Intent(
                Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkedin_profile_url))))
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.label_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        }
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }
}

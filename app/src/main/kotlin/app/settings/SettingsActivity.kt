package app.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.include_toolbar.*
import org.stoyicker.dinger.R

internal class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setupToolbar()
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

package app.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import app.settings.SettingsActivity
import kotlinx.android.synthetic.main.include_home_pager.home_pager
import kotlinx.android.synthetic.main.include_toolbar.toolbar
import org.stoyicker.dinger.R

internal class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        setContentPager()
    }

    override fun onCreateOptionsMenu(menu: Menu) = super.onCreateOptionsMenu(menu).also {
        menuInflater.inflate(R.menu.activity_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> true.also { startActivity(SettingsActivity.getCallingIntent(this)) }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setContentPager() {
        home_pager.adapter = HomeFragmentPagerAdapter(supportFragmentManager)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}

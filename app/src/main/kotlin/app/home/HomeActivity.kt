package app.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.stoyicker.dinger.R

internal class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}

package app.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.content.startIntent
import android.net.Uri
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import org.stoyicker.dinger.R

internal class SettingsPreferenceFragmentCompat : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_settings, rootKey)
        initializeSharePreference()
        initializeAboutTheAppPreference()
    }

    private fun initializeSharePreference() {
        findPreference(context?.getString(R.string.preference_key_share))
                .onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                @SuppressLint("InlinedApi")
                flags += Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                putExtra(Intent.EXTRA_TEXT, context?.getString(
                        R.string.dinger_website_download))
            }
            context?.startIntent(Intent.createChooser(
                    intent, getString(R.string.action_share_title)))
            true
        }
    }

    private fun initializeAboutTheAppPreference() {
        findPreference(context?.getString(R.string.preference_key_about_the_app))
                .onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context?.startIntent(Intent(
                    Intent.ACTION_VIEW, Uri.parse(getString(R.string.dinger_website))))
            true
        }
    }

    companion object {
        const val FRAGMENT_TAG = "SettingsPreferenceFragmentCompat"
    }
}

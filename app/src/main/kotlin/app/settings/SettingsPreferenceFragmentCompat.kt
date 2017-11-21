package app.settings

import android.content.Intent
import android.content.startIntent
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import org.stoyicker.dinger.R

internal class SettingsPreferenceFragmentCompat : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_settings, rootKey)
        initializeSharePreference()
    }

    private fun initializeSharePreference() {
        findPreference(context?.getString(R.string.preference_key_share))
                .onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                flags += Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                putExtra(Intent.EXTRA_TEXT, context?.getString(
                        R.string.dinger_website_download))
            }
            context?.startIntent(Intent.createChooser(
                    intent, getString(R.string.action_share_title)))
            true
        }
    }

    companion object {
        const val FRAGMENT_TAG = "SettingsPreferenceFragmentCompat"
    }
}

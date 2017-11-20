package app.settings

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import org.stoyicker.dinger.R

internal class SettingsPreferenceFragmentCompat : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_settings, rootKey)
    }

    companion object {
        const val FRAGMENT_TAG = "SettingsPreferenceFragmentCompat"
    }
}

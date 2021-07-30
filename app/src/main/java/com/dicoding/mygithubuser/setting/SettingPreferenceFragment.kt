package com.dicoding.mygithubuser.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.mygithubuser.R

class SettingPreferenceFragment : PreferenceFragmentCompat() {

    private lateinit var time: String
    private lateinit var timeSetting: SwitchPreference
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preference)

        alarmReceiver = AlarmReceiver()

        time = resources.getString(R.string.time_setting)
        timeSetting = findPreference<SwitchPreference>(time) as SwitchPreference

        timeSetting.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == true) {
                val repeatMessage = "Let's find favorite user on Github"
                alarmReceiver.setRepeatingAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING, repeatMessage)
            } else {
                alarmReceiver.cancelAlarm(requireContext())
            }
            true
        }
    }

}
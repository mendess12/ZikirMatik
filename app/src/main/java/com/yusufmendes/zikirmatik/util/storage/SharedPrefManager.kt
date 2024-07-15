package com.yusufmendes.zikirmatik.util.storage

import android.content.Context
import javax.inject.Inject

class SharedPrefManager @Inject constructor(
    private val context: Context
) {

    val sharedPreferences = context.getSharedPreferences("CounterPref", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    fun getCounter(): Int {
        return sharedPreferences.getInt("counter", 0)
    }

    fun saveCounter(counter: Int) {
        editor.putInt("counter", counter)
        editor.apply()
    }

    fun getVibrateState(): Int {
        return sharedPreferences.getInt("vibrate", 0)
    }

    fun saveVibrateState(state: Int) {
        editor.putInt("vibrate", state)
        editor.apply()
    }
}
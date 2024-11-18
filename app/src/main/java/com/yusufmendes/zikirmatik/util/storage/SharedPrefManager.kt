package com.yusufmendes.zikirmatik.util.storage

import android.content.Context
import com.google.gson.Gson
import com.yusufmendes.zikirmatik.data.model.CounterEntity
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

    fun isNavArgs(isNavArgs: Boolean) {
        editor.putBoolean("navArgs", isNavArgs)
        editor.apply()
    }

    fun getIsNavArgs(): Boolean {
        return sharedPreferences.getBoolean("navArgs", false)
    }

    fun saveNavArgs(count: CounterEntity) {
        val countGson = Gson().toJson(count)
        editor.putString("count", countGson)
        editor.apply()
    }

    fun getNavArgs(): CounterEntity? {
        val jsonString = sharedPreferences.getString("count", null)
        return if (jsonString != null) Gson().fromJson(
            jsonString,
            CounterEntity::class.java
        ) else null
    }

}
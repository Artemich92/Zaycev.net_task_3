package ru.artkolest.zaycevnet_task_3

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class App : Application() {

    private lateinit var mSettings: SharedPreferences
    private var count = 1

    override fun onCreate() {
        super.onCreate()

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        if (mSettings.contains(COUNT_LAUNCHES)) {
            count = mSettings.getInt(COUNT_LAUNCHES, 0)
            count++
            if (count == 3) {
                Toast.makeText(applicationContext, R.string.toast_text, Toast.LENGTH_LONG).show()
            }
        }
        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.putInt(COUNT_LAUNCHES, count)
        editor.apply()
    }

    companion object {
        private const val APP_PREFERENCES = "mySettings"
        private const val COUNT_LAUNCHES = "countLaunches"
    }
}
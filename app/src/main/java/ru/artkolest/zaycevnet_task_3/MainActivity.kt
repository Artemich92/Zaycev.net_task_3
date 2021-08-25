package ru.artkolest.zaycevnet_task_3

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var count: Int = 1
    private lateinit var mSettings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT_LAUNCHES)
        } else if (mSettings.contains(COUNT_LAUNCHES)) {
            count = mSettings.getInt(COUNT_LAUNCHES, 0)
            count++
            if (count == 3) {
                Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_LAUNCHES, count)
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.putInt(COUNT_LAUNCHES, count)
        editor.apply()
    }

    companion object {
        private const val APP_PREFERENCES = "mySettings"
        private const val COUNT_LAUNCHES = "countLaunches"
    }
}
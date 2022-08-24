package no.larseknu.hiof.playingwithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class OtherActivity : AppCompatActivity() {
    private val LOG_TAG = "PlayingWithOTHER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        Log.d(LOG_TAG, "onCreate() ran")
    }

    override fun onStart() {
        super.onStart()

        Log.d(LOG_TAG, "onStart() ran")
    }

    override fun onResume() {
        super.onResume()

        Log.d(LOG_TAG, "onResume() ran")
    }

    override fun onPause() {
        super.onPause()

        Log.d(LOG_TAG, "onPause() ran")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(LOG_TAG, "onRestart() ran")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(LOG_TAG, "onDestroy() ran")
    }
}
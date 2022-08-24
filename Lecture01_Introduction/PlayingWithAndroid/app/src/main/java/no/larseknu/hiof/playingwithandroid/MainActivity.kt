package no.larseknu.hiof.playingwithandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "PlayingWithAndroid"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "onCreate() ran")

        val otherButton = findViewById<Button>(R.id.other_button)
        otherButton.setOnClickListener {
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent)
        }
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
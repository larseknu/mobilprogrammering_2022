package no.larseknu.hiof.myfirstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class OtherActivity : AppCompatActivity() {
    val LOG_TAG = "LifeCycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

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

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop() ran")
    }
}
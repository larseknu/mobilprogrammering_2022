package no.larseknu.hiof.playingwithmaterialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DynamicColors.applyToActivityIfAvailable(this)
    }
}
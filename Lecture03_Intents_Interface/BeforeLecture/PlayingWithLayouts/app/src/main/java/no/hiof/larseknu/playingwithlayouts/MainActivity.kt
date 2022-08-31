package no.hiof.larseknu.playingwithlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_contraint_layout_view_gone.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_constraint_layout_basics)
    }

}

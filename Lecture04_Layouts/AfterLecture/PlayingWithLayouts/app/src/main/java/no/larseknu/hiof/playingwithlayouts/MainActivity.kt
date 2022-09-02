package no.larseknu.hiof.playingwithlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_basics)
        //setContentView(R.layout.activity_linear_layout_basics)
        //setContentView(R.layout.activity_constraint_layout_chains)
        //setContentView(R.layout.activity_constraint_layout_spotify)
        //setContentView(R.layout.activity_contraint_layout_plex)


        /*
        // Code for showing what happens when we set a view to gone during runtime
        setContentView(R.layout.activity_contraint_layout_view_gone)

        val removeViewButton = findViewById<Button>(R.id.buttonRemoveView)

        removeViewButton.setOnClickListener {
            val textViewA = findViewById<TextView>(R.id.textViewA)
            if (textViewA.visibility == View.VISIBLE)
                textViewA.visibility = View.GONE
            else
                textViewA.visibility = View.VISIBLE
        }*/
    }
}
package no.larseknu.hiof.myfirstandroidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import no.larseknu.hiof.myfirstandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // We don't have to explicitly get these when we have set up view binding
        /* val submitButton = findViewById<Button>(R.id.submitButton)
        val textInput = findViewById<EditText>(R.id.inputEditText)
        val openWebPageButton = findViewById<Button>(R.id.openWebPageButton)
        val openMapsButton = findViewById<Button>(R.id.openMapsButton) */

        binding.submitButton.setOnClickListener {
            val otherActivityIntent = Intent(it.context, OtherActivity::class.java)
            val userText = binding.inputEditText.text.toString()

            otherActivityIntent.putExtra(OtherActivity.KEY_NAME, userText)

            startActivity(otherActivityIntent)
        }

        binding.openWebPageButton.setOnClickListener {
            val openWebPageIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hiof.no"))

            startActivity(openWebPageIntent)
        }

        binding.openMapsButton.setOnClickListener {
            //val uri = Uri.parse("https://maps.google.com?q=59.128708,11.353176");
            val uri = Uri.parse("geo:59.128708,11.353176")

            val intent = Intent(Intent.ACTION_VIEW, uri)

            startActivity(intent)
        }
    }
}
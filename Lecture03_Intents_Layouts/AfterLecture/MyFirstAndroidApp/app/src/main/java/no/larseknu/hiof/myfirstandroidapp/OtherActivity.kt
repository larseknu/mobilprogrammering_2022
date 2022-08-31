package no.larseknu.hiof.myfirstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import no.larseknu.hiof.myfirstandroidapp.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {
    val LOG_TAG = "LifeCycle"

    companion object {
        const val KEY_NAME = "input_text"
    }

    private lateinit var binding: ActivityOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userText = intent?.extras?.getString(KEY_NAME)

        binding.textView.text = userText

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            uri ->
            // Callback is invoked after the user selects a media item or closes the photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                binding.imageView.setImageURI(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
                Toast.makeText(this, "No image selected", Toast.LENGTH_LONG).show()
            }
        }


        binding.getPictureButton.setOnClickListener {
            Toast.makeText(this, "Getting picture", Toast.LENGTH_SHORT).show();

            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop() ran")
    }
}
package no.larseknu.hiof.firebaseplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        createAuthenticationListener()

        Log.d("AuthUser", "User: " + auth.currentUser?.email)
    }

    override fun onResume() {
        super.onResume()

        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()

        auth.removeAuthStateListener(authStateListener)
    }

    private fun createAuthenticationListener() {
        authStateListener = FirebaseAuth.AuthStateListener {
            val firebaseUser = auth.currentUser
            if (firebaseUser == null) {
                createSignInIntent()
            }
            else {
                Toast.makeText(this, "You are signed in as: " + Firebase.auth.currentUser?.displayName, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            val user = auth.currentUser
            Toast.makeText(this, "You are signed in as: " + user?.displayName, Toast.LENGTH_SHORT).show()
        } else {
            Log.d("AuthUser", "Sign in failed")
        }
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signInLauncher.launch(signInIntent)
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOut -> {
                auth.signOut()
                return true
            }
        }

        return false
    }
}
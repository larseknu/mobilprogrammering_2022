package no.larseknu.hiof.playingwithnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get a reference to the loginButton.
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)
        val editTextUserName = view.findViewById<EditText>(R.id.editTextUserName)
        val editTextPassword = view.findViewById<EditText>(R.id.editTextPassword)


        // We can set the onClickListener that's returned from the createNavigationOnClickListener method
        // loginButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_homeFragment))

        //  But if we want som logic other than just navigating, we can use the lambda expression to set to the onClickListener
        loginButton.setOnClickListener {
            // Get reference to the navigation controller through the view (it)
            val navController = it.findNavController()

            if (editTextUserName.text.toString().isNotEmpty() && editTextPassword.text.toString().isNotEmpty()) {
                // Get the action from the safeargs generated LoginFragmentDirections-class
                val action = LoginFragmentDirections.actionLoginToHome()
                // Set the username
                action.username = editTextUserName.text.toString()

                // Navigate with the action that includes the arguments
                navController.navigate(action)
            }
            else
                Toast.makeText(it.context, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }
}
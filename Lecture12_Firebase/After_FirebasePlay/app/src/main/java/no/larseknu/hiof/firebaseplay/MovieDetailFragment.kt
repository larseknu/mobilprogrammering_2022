package no.larseknu.hiof.firebaseplay

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import no.larseknu.hiof.firebaseplay.databinding.FragmentMovieDetailBinding
import no.larseknu.hiof.firebaseplay.model.Movie

class MovieDetailFragment : Fragment() {
    private val args : MovieDetailFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var storage : FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        storage = Firebase.storage

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gets the movie with the uid (just a number in the list in this case)
        val documentReference =  FirebaseFirestore.getInstance().collection("movies").document(args.uid)

        documentReference.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documentSnapshot = task.result!!
                val movie = documentSnapshot.toObject(Movie::class.java)!!

                // Fills up the views with the movie-information, accessing the views through the binding-object
                binding.movieTitleTextView.text = movie.title
                binding.movieDescriptionTextView.text = movie.description

                if (movie.posterUrl.isNotEmpty()) {
                    // Gets the movie with the uid (just a number in the list in this case)
                    val reference =  storage.getReference("/movie_posters/").child(movie.posterUrl)

                    Glide.with(this)
                        .load(reference)
                        .into(binding.moviePosterImageView)
                }

            } else {
                Log.d("MovieDetailFragment", "Get failed with", task.exception)
            }
        }


    }
}
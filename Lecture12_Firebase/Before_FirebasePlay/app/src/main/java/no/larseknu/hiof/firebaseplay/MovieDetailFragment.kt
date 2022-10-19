package no.larseknu.hiof.firebaseplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import no.larseknu.hiof.firebaseplay.databinding.FragmentMovieDetailBinding
import no.larseknu.hiof.firebaseplay.model.Movie

class MovieDetailFragment : Fragment() {
    private val args : MovieDetailFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gets the movie with the uid (just a number in the list in this case)
        val movie = Movie.getMovies()[args.uid]

        // Fills up the views with the movie-information, accessing the views through the binding-object
        binding.movieTitleTextView.text = movie.title
        binding.moviePosterImageView.setImageResource(R.drawable.poster_placeholder)
        binding.movieDescriptionTextView.text = movie.description
    }
}
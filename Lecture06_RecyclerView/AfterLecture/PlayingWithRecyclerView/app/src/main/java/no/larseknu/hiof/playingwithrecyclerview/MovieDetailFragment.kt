package no.larseknu.hiof.playingwithrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import no.larseknu.hiof.playingwithrecyclerview.databinding.FragmentMovieDetailBinding
import no.larseknu.hiof.playingwithrecyclerview.model.Movie

class MovieDetailFragment : Fragment() {
    private val args : MovieDetailFragmentArgs by navArgs()

    private var fragmentBinding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailBinding.bind(view)
        fragmentBinding = binding


        // Gets the movie with the uid (just a number in the list in this case)
        val movie = Movie.getMovies()[args.uid]

        // Fills up the views with the movie-information
        /* view.findViewById<TextView>(R.id.movieTitleTextView).text = movie.title
        view.findViewById<ImageView>(R.id.moviePosterImageView).setImageResource(movie.posterUrl)
        view.findViewById<TextView>(R.id.movieDescriptionTextView).text = movie.description*/


        // Fills up the views with the movie-information, accessing the views through the binding-object
        binding.movieTitleTextView.text = movie.title
        binding.moviePosterImageView.setImageResource(movie.posterUrl)
        binding.movieDescriptionTextView.text = movie.description
    }
}
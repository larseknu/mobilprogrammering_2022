package no.larseknu.hiof.viewmodelplay.screens.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import no.larseknu.hiof.viewmodelplay.R
import no.larseknu.hiof.viewmodelplay.databinding.FragmentMovieSelectionBinding
import no.larseknu.hiof.viewmodelplay.model.Movie


class MovieSelectionFragment : Fragment() {
    private var _binding: FragmentMovieSelectionBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private var movieList : ArrayList<Movie> = ArrayList(Movie.getMovies())
    private lateinit var currentMovie: Movie
    private var numberAnswered = 0
    private var numberSeen = 0
    private var selectionFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seenButton.setOnClickListener { seenMovie() }
        binding.notSeenButton.setOnClickListener { notSeenMovie() }
        binding.doneButton.setOnClickListener { movieSelectionFinished() }

        nextMovie()
        updateMovieInUI()
    }

    private fun movieSelectionFinished() {
        Toast.makeText(activity, "Movie selection finished", Toast.LENGTH_SHORT).show()
    }

    /**
     * These click-handler methods are not needed when we have set it up with binding to the ViewModel
     */
    private fun seenMovie() {
        numberSeen++
        numberAnswered++
        nextMovie()
        updateMovieInUI()
    }

    private fun notSeenMovie() {
        numberAnswered++
        nextMovie()
        updateMovieInUI()
    }

    /**
    * These are not needed when we use an observer
    */
    private fun updateMovieInUI() {
        if (!selectionFinished) {
            updatePoster()
            updateTitle()
            updateSeenText()
        }
    }

    private fun updateSeenText() {
        binding.numberSeenOfAnsweredText.text = getString(R.string.number_seen_of_answered, numberSeen, numberAnswered)
    }

    private fun updateTitle() {
        binding.movieTitle.text = currentMovie.title
    }

    private fun updatePoster() {
        binding.moviePoster.setImageResource(currentMovie.posterUrl)
    }


    private fun nextMovie() {
        if (movieList.isNotEmpty()) {
            currentMovie = movieList.removeAt(0)
        }
        else {
            Toast.makeText(activity, "No more movies!", Toast.LENGTH_SHORT).show()
            selectionFinished = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
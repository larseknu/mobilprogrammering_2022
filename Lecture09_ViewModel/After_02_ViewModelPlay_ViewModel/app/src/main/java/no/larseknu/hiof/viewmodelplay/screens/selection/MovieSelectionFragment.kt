package no.larseknu.hiof.viewmodelplay.screens.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import no.larseknu.hiof.viewmodelplay.R
import no.larseknu.hiof.viewmodelplay.databinding.FragmentMovieSelectionBinding


class MovieSelectionFragment : Fragment() {
    private var _binding: FragmentMovieSelectionBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieSelectionViewModel by viewModels()


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

        updateMovieInUI()
    }

    private fun movieSelectionFinished() {
        Toast.makeText(activity, "Movie selection finished", Toast.LENGTH_SHORT).show()
        val action = MovieSelectionFragmentDirections.actionMovieSelectionToSummary()
        action.numberAnswered = viewModel.numberAnswered
        action.numberSeen = viewModel.numberSeen
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * These click-handler methods are not needed when we have set it up with binding to the ViewModel
     */
    private fun seenMovie() {
        viewModel.seenMovie()
        updateMovieInUI()
    }

    private fun notSeenMovie() {
        viewModel.notSeenMovie()
        updateMovieInUI()
    }

    /**
    * These are not needed when we use an observer
    */
    private fun updateMovieInUI() {
        updatePoster()
        updateTitle()
        updateSeenText()
    }

    private fun updateSeenText() {
        binding.numberSeenOfAnsweredText.text = getString(R.string.number_seen_of_answered, viewModel.numberSeen, viewModel.numberAnswered)
    }

    private fun updateTitle() {
        binding.movieTitle.text = viewModel.currentMovie.title
    }

    private fun updatePoster() {
        binding.moviePoster.setImageResource(viewModel.currentMovie.posterUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
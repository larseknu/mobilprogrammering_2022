package no.larseknu.hiof.viewmodelplay.screens.summary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import no.larseknu.hiof.viewmodelplay.R
import no.larseknu.hiof.viewmodelplay.databinding.FragmentMovieSelectionBinding
import no.larseknu.hiof.viewmodelplay.databinding.FragmentMovieSummaryBinding

class MovieSummaryFragment : Fragment() {
    private var _binding: FragmentMovieSummaryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    private val viewModel : MovieSummaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieSummaryBinding.inflate(inflater, container, false)

        val args = MovieSummaryFragmentArgs.fromBundle(requireArguments())

        viewModel.numberAnswered = args.numberAnswered
        viewModel.numberSeen = args.numberSeen

        binding.numberSeenOfAnsweredText.text = getString(R.string.number_seen_of_answered, viewModel.numberSeen, viewModel.numberAnswered)

        binding.progressBar.max = viewModel.numberAnswered
        binding.progressBar.progress = viewModel.numberSeen

        binding.tryAgainButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.actionStartSelectionAgainFromSummary)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
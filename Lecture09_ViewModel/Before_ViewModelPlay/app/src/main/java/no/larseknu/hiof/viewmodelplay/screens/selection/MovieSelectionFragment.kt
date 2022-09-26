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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
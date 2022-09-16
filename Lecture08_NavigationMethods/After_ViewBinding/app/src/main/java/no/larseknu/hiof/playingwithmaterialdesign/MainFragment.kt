package no.larseknu.hiof.playingwithmaterialdesign

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import no.larseknu.hiof.playingwithmaterialdesign.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var counter = 0
    private var increment = 1

    companion object {
        private const val COUNT_ID = "COUNT_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val countButton = view.findViewById<Button>(R.id.countButton)
        //val counterTextView = view.findViewById<TextView>(R.id.counterTextView)
        //val incrementTextView = view.findViewById<TextView>(R.id.incrementTextView)

        if (savedInstanceState != null)
            counter = savedInstanceState.getInt(COUNT_ID, 1)

        binding.counterTextView.text = counter.toString()
        binding.incrementTextView.text = "+$increment"

        binding.countButton.setOnClickListener {
            counter += increment
            binding.counterTextView.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(COUNT_ID, counter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package no.larseknu.hiof.viewmodelplay.screens.selection

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import no.larseknu.hiof.viewmodelplay.model.Movie

class MovieSelectionViewModel : ViewModel() {
    private lateinit var movieList : MutableList<Movie>

    val currentMovie = MutableLiveData<Movie>()
    val numberAnswered = MutableLiveData<Int>()
    val numberSeen = MutableLiveData<Int>()

    init {
        numberAnswered.value = 0
        numberSeen.value = 0

        retrieveList()
        nextMovie()
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun seenMovie() {
        numberSeen.value = numberSeen.value?.plus(1)
        numberAnswered.value = numberAnswered.value?.plus(1)

        nextMovie()
    }

    fun notSeenMovie() {
        numberAnswered.value = numberAnswered.value?.plus(1)

        nextMovie()
    }

    private fun nextMovie() {
        if (movieList.isNotEmpty()) {
            currentMovie.value = movieList.removeAt(0)
        }
    }

    private fun retrieveList() {
        movieList = ArrayList(Movie.getMovies())
        movieList.shuffle()
    }

}
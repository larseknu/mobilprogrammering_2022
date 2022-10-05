package no.larseknu.hiof.viewmodelplay.screens.selection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import no.larseknu.hiof.viewmodelplay.data.Movie

class MovieSelectionViewModel : ViewModel() {
    private lateinit var movieList : MutableList<Movie>

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie>
        get() = _currentMovie

    private val _numberAnswered = MutableLiveData<Int>()
    val numberAnswered: LiveData<Int>
        get() = _numberAnswered

    private val _numberSeen = MutableLiveData<Int>()
    val numberSeen: LiveData<Int>
        get() = _numberSeen

    private val _selectionDone = MutableLiveData<Boolean>()
    val selectionDone: LiveData<Boolean>
        get() = _selectionDone


    init {
        _numberAnswered.value = 0
        _numberSeen.value = 0
        _selectionDone.value = false

        retrieveList()
        nextMovie()
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun seenMovie() {
        _numberSeen.value = numberSeen.value?.plus(1)
        _numberAnswered.value = numberAnswered.value?.plus(1)

        nextMovie()
    }

    fun notSeenMovie() {
        _numberAnswered.value = numberAnswered.value?.plus(1)

        nextMovie()
    }

    private fun nextMovie() {
        if (movieList.isNotEmpty()) {
            _currentMovie.value = movieList.removeAt(0)
        }
        else
            _selectionDone.value = true
    }

    private fun retrieveList() {
        movieList = ArrayList(Movie.getMovies())
        movieList.shuffle()
    }

}
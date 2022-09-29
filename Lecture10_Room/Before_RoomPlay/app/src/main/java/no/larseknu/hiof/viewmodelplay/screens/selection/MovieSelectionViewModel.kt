package no.larseknu.hiof.viewmodelplay.screens.selection

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import no.larseknu.hiof.viewmodelplay.model.Movie

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

    private val _eventSelectionDone = MutableLiveData<Boolean>()
    val eventSelectionDone: LiveData<Boolean>
        get() = _eventSelectionDone

    init {
        _numberAnswered.value = 0
        _numberSeen.value = 0

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
        else {
            onSelectionDone()
        }
    }

    /** Method for when we are done with the selection **/
    fun onSelectionDone() {
        _eventSelectionDone.value = true
    }

    fun onSelectionDoneReset() {
        _eventSelectionDone.value = false
    }

    private fun retrieveList() {
        movieList = ArrayList(Movie.getMovies())
        movieList.shuffle()
    }

}
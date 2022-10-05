package no.larseknu.hiof.roomplay.screens.selection

import android.util.Log
import androidx.lifecycle.*
import no.larseknu.hiof.roomplay.data.Movie
import no.larseknu.hiof.roomplay.data.MovieRepository

class MovieSelectionViewModel(private val repository: MovieRepository) : ViewModel() {
    var movieList : LiveData<List<Movie>> = repository.getMovies().asLiveData()

    private var currentMovieIndex = 0

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

        nextMovie()
        Log.i("MovieViewModel", "MovieViewModel created!")
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

    fun showMovie() {
        _currentMovie.value = movieList.value!!.get(currentMovieIndex)
    }

    private fun nextMovie() {
        Log.d("UJ - MovieSelection", _currentMovie.value?.title + " - the list - " + movieList.value?.size)

        movieList.value ?.let {
            if (currentMovieIndex < movieList.value!!.size-1) {
                currentMovieIndex++
                _currentMovie.value = movieList.value!!.get(currentMovieIndex)
            }
            else {
                onSelectionDone()
            }
        }
    }

    /** Method for when we are done with the selection **/
    fun onSelectionDone() {
        _eventSelectionDone.value = true
    }

    fun onSelectionDoneReset() {
        _eventSelectionDone.value = false
    }
}

class MovieSelectionViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieSelectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieSelectionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package no.larseknu.hiof.viewmodelplay.screens.summary

import android.util.Log
import androidx.lifecycle.ViewModel

class MovieSummaryViewModel : ViewModel() {
    var numberAnswered: Int = 0
    var numberSeen: Int = 0

    init {
        Log.i("MovieSummaryViewModel", "MovieSummaryViewModel created")
    }
}
package no.larseknu.hiof.roomplay

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import no.larseknu.hiof.roomplay.data.MovieDatabase
import no.larseknu.hiof.roomplay.data.MovieRepository

class MovieApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MovieDatabase.getInstance(this, applicationScope) }
    val repository by lazy { MovieRepository(database.movieDao()) }
}
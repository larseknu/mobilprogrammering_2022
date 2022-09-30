package no.larseknu.hiof.roomplay.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(var title : String, var description : String, var posterUrl : Int) {

    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
}


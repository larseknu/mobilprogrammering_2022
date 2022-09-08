package no.larseknu.hiof.playingwithrecyclerview.model

import no.larseknu.hiof.playingwithrecyclerview.R

data class Movie(val uid : Int, var title : String, var description : String, var posterUrl : Int) {

    companion object {
        fun getMovies() : List<Movie> {
            return listOf(
                Movie(1, "Captain America Civil War", "A cool movie!", R.drawable.captain_america_civil_war),
                Movie(2, "Donnie Darko", "A cool movie!", R.drawable.donnie_darko),
                Movie(3, "Iron Man 3", "A cool movie!", R.drawable.iron_man_3),
                Movie(4, "Spirited Away", "A cool movie!", R.drawable.spirited_away),
                Movie(5, "Star Wars The Force Awakens", "A cool movie!", R.drawable.star_wars_the_force_awakens),
                Movie(6, "The Hobbit", "A cool movie!", R.drawable.the_hobbit),
                Movie(7, "Up", "A cool movie!", R.drawable.up),
                Movie(8, "Pulp Fiction", "A cool movie!", R.drawable.pulp_fiction),
                Movie(9, "Coco", "A cool movie!", R.drawable.coco),
                Movie(10, "Deadpool", "A cool movie!", R.drawable.deadpool),
                Movie(11, "Inside Out", "A cool movie!", R.drawable.inside_out),
                Movie(12, "Into The Wild", "A cool movie!", R.drawable.into_the_wild),
                Movie(13, "The Hateful Eight", "A cool movie!", R.drawable.the_hateful_eight),
                Movie(14, "The Intouchables", "A cool movie!", R.drawable.the_intouchables),
                Movie(15, "The Lion King", "A cool movie!", R.drawable.the_lion_king),
                Movie(13, "Bo Burnham: Inside", "A cool movie!", R.drawable.inside),
                Movie(14, "Your Name", "A cool movie!", R.drawable.your_name),
                Movie(15, "Spider-Man: No Way Home", "A cool movie!", R.drawable.spiderman_no_way_home),
            )
        }
    }

}
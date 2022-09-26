package no.larseknu.hiof.viewmodelplay.model

import no.larseknu.hiof.viewmodelplay.R

data class Movie(val uid : Int, var title : String, var description : String, var posterUrl : Int) {

    companion object {
        fun getMovies() : List<Movie> {
            return listOf(
                Movie(0, "Captain America Civil War", "A cool movie!", R.drawable.captain_america_civil_war),
                Movie(1, "Donnie Darko", "A cool movie!", R.drawable.donnie_darko),
                Movie(2, "Iron Man 3", "A cool movie!", R.drawable.iron_man_3),
                Movie(3, "Spirited Away", "A cool movie!", R.drawable.spirited_away),
                Movie(4, "Star Wars The Force Awakens", "A cool movie!", R.drawable.star_wars_the_force_awakens),
                Movie(5, "The Hobbit", "A cool movie!", R.drawable.the_hobbit),
                Movie(6, "Up", "A cool movie!", R.drawable.up),
                Movie(7, "Pulp Fiction", "A cool movie!", R.drawable.pulp_fiction),
                Movie(8, "Coco", "A cool movie!", R.drawable.coco),
                Movie(9, "Deadpool", "A cool movie!", R.drawable.deadpool),
                Movie(10, "Inside Out", "A cool movie!", R.drawable.inside_out),
                Movie(11, "Into The Wild", "A cool movie!", R.drawable.into_the_wild),
                Movie(12, "The Hateful Eight", "A cool movie!", R.drawable.the_hateful_eight),
                Movie(13, "The Intouchables", "A cool movie!", R.drawable.the_intouchables),
                Movie(14, "The Lion King", "A cool movie!", R.drawable.the_lion_king),
                Movie(15, "Bo Burnham: Inside", "A cool movie!", R.drawable.inside),
                Movie(16, "Your Name", "A cool movie!", R.drawable.your_name),
                Movie(17, "Spider-Man: No Way Home", "A cool movie!", R.drawable.spiderman_no_way_home),
            )
        }
    }

}
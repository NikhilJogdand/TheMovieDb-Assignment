package demo.moviedb.ui.main.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import demo.moviedb.R
import demo.moviedb.data.database.entities.PopularEntry
import demo.moviedb.data.models.Movie
import demo.moviedb.ui.base.interfaces.OnMovieClickListener


class MovieViewHolder(
    itemView: View?
) : RecyclerView.ViewHolder(itemView!!) {

    var movieTitle: TextView
    var movieReleaseDate: TextView
    var movieVisitedDate: TextView
    var moviePoster: ImageView
    var movieOverview: TextView
    var movieDetails: LinearLayout

    init {
        movieTitle = itemView!!.findViewById(R.id.single_item_movie_title)
        movieReleaseDate = itemView.findViewById(R.id.single_item_movie_release_date)
        movieVisitedDate = itemView.findViewById(R.id.single_item_movie_recent_visited_date)
        moviePoster = itemView.findViewById(R.id.single_item_movie_image)
        movieOverview = itemView.findViewById(R.id.single_item_movie_overview)
        movieDetails = itemView.findViewById(R.id.single_item_movie_details)
    }

}
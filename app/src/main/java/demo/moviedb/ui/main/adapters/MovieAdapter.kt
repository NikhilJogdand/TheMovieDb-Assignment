package demo.moviedb.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import demo.moviedb.R
import demo.moviedb.data.database.entities.PopularEntry
import demo.moviedb.ui.base.interfaces.OnMovieClickListener
import demo.moviedb.ui.main.viewholders.MovieViewHolder
import demo.moviedb.utils.Constants.Companion.CONTENT_MOVIE
import demo.moviedb.utils.DateUtils
import demo.moviedb.utils.Helpers.buildImageUrl


class MovieAdapter(
    movieList: List<PopularEntry>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var content: Context
    private var movieList: List<PopularEntry>

    init {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        content = parent.context
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_single_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            CONTENT_MOVIE -> {
                val movieViewHolder = holder as MovieViewHolder
                val movie: PopularEntry = movieList.get(holder.adapterPosition)

                movieViewHolder.movieTitle.text = movie.title
                movieViewHolder.movieReleaseDate.text =
                    "Release Date: ".plus(DateUtils.getStringDate(movie.releaseDate!!))
                movieViewHolder.movieVisitedDate.text =
                    "Last Visited: ".plus(DateUtils.formatRecentVisitedDateTime(movie.recentVisitedDateTime!!))
                movieViewHolder.movieOverview.text = movie.overview

                Glide.with(content).load(buildImageUrl(movie.posterPath!!)).thumbnail(0.05f)
                    .apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    )
                    .transition(withCrossFade()).into(movieViewHolder.moviePoster)
            }

        }

    }

    override fun getItemCount(): Int = movieList.size
}
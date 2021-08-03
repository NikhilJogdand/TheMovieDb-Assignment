package demo.moviedb.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import demo.moviedb.API_KEY.Companion.TMDB_API_KEY
import demo.moviedb.data.database.entities.PopularEntry
import demo.moviedb.data.database.localCache.PopularLocalCache
import demo.moviedb.data.models.Movie
import demo.moviedb.data.models.MovieDetail
import demo.moviedb.data.network.NetworkService
import demo.moviedb.utils.Constants
import demo.moviedb.utils.DateUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieDetailsRepository(private val providePopularCache: PopularLocalCache) {

    private val networkService: NetworkService = NetworkService.instance

    fun getMovieDetails(movieId: String): LiveData<MovieDetail> {

        val movieDetails: MutableLiveData<MovieDetail> = MutableLiveData<MovieDetail>()
        networkService.tmdbApi.getDetailMovie(movieId, TMDB_API_KEY, "videos")
            .enqueue(object : Callback<MovieDetail> {
                override fun onFailure(call: Call<MovieDetail>?, t: Throwable?) {
                    Log.i("MovieDetails Error", "Details and Video fetch failed")
                }

                override fun onResponse(
                    call: Call<MovieDetail>?,
                    response: Response<MovieDetail>?
                ) {
                    movieDetails.value = response!!.body()
                }
            })
        return movieDetails
    }

    fun updatePopularMovieVisitedDateTime(data : Movie): Boolean {
        val popularEntry = PopularEntry()
        val movie = data
        popularEntry.movieId = movie.id
        popularEntry.voteCount = movie.voteCount
        popularEntry.video = movie.video
        popularEntry.voteAverage = movie.voteAverage
        popularEntry.title = movie.title
        popularEntry.popularity = movie.popularity
        popularEntry.posterPath = movie.posterPath
        popularEntry.originalLanguage = movie.originalLanguage
        popularEntry.originalTitle = movie.originalTitle
        popularEntry.genreIds = movie.genreString
        popularEntry.backdropPath = movie.backdropPath
        popularEntry.adult = movie.adult
        popularEntry.overview = movie.overview
        popularEntry.releaseDate = movie.releaseDate
        popularEntry.contentType = Constants.CONTENT_SIMILAR
        popularEntry.timeAdded = Date().time
        val dateTime = DateUtils.getCurrentDateTime()
        Log.d("nik", "Date Visited: $dateTime")
        popularEntry.recentVisitedDateTime = dateTime

        var isUpdated = false
        providePopularCache.updatePopularMovie(popularEntry) {
            isUpdated = true
        }
        return isUpdated
    }

}
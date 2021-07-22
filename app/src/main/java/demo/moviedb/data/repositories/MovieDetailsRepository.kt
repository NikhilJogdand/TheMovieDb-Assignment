package demo.moviedb.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import demo.moviedb.API_KEY.Companion.TMDB_API_KEY
import demo.moviedb.data.models.MovieDetail
import demo.moviedb.data.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsRepository {

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

}
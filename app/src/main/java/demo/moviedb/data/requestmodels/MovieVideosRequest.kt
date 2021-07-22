package demo.moviedb.data.requestmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import demo.moviedb.data.models.MovieVideo

class MovieVideosRequest {
    @SerializedName("results")
    @Expose
    var videos: List<MovieVideo>? = null
}
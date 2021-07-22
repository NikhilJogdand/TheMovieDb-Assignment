package demo.moviedb.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import demo.moviedb.data.requestmodels.MovieVideosRequest

class MovieDetail {

    @SerializedName("videos")
    @Expose()
    var videosResult: MovieVideosRequest? = null

}
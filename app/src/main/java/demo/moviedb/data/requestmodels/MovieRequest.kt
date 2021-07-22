package demo.moviedb.data.requestmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import demo.moviedb.data.models.Movie

class MovieRequest {
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0

    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null
}
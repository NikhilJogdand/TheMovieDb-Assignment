package demo.moviedb.ui.main.views.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import demo.moviedb.R
import demo.moviedb.ui.main.views.fragments.PopularMoviesFragment

class MoviesActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initViews()
        setupToolBar()
        addFragment()

    }

    private fun initViews() {
        toolBar = findViewById(R.id.activity_movies_toolbar)
    }

    private fun setupToolBar() {
        toolBar.title = getString(R.string.app_name)
        setSupportActionBar(toolBar)
    }

    private fun addFragment() {
        val popularMoviesFragment = PopularMoviesFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        supportFragmentManager.popBackStack(popularMoviesFragment.javaClass.name, 0)
        fragmentTransaction.replace(
            R.id.rl_frag_container,
            popularMoviesFragment,
            popularMoviesFragment.javaClass.name
        )
        fragmentTransaction.commit()
    }

}
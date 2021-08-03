package demo.moviedb.ui.main.views.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import demo.moviedb.R
import demo.moviedb.ui.main.views.fragments.PopularMoviesFragment
import demo.moviedb.ui.main.views.fragments.RecentMovieHistoryFragment

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

    fun addRecentMoviesFragment() {
        val popularMoviesFragment = RecentMovieHistoryFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        supportFragmentManager.popBackStack(popularMoviesFragment.javaClass.name, 0)
        fragmentTransaction.replace(R.id.rl_frag_container, popularMoviesFragment, popularMoviesFragment.javaClass.name)
        //check fragment already exist then not set it in add to back stack
        val fragmentExits = fragmentManager.findFragmentByTag(popularMoviesFragment.javaClass.name)
        if (fragmentExits == null) {
            // not exist
            //   fragmentTransaction.addToBackStack(fragment.javaClass.name)
            fragmentTransaction.addToBackStack(popularMoviesFragment.javaClass.name)
        }

        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
            super.onBackPressed()
        }
        supportActionBar?.title = "Popular Movies"
        super.onBackPressed()
    }

}
package com.nicolag.accessibilitydemo.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.App
import com.nicolag.accessibilitydemo.model.action.MainViewAction
import com.nicolag.accessibilitydemo.model.action.MainViewClick
import com.nicolag.accessibilitydemo.model.event.MainViewEvent
import com.nicolag.accessibilitydemo.model.state.MainViewState
import com.nicolag.accessibilitydemo.model.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: MainViewModel.MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        viewModelFactory.getViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectActivity()

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        observeViewModel()
        load()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun observeViewModel() {
        viewModel.state().observe(this, Observer<MainViewState> {
            it?.let { renderViewState(it) }
        })
        viewModel.event().observe(this, Observer<MainViewEvent> {
            it?.let { renderViewEvent(it) }
        })
    }

    private fun renderViewState(state: MainViewState) {
        welcomeTextView.text = state.welcomeText
        fab.setOnClickListener {
            viewModel.dispatch(MainViewAction.Click(MainViewClick.Fab))
        }
    }

    private fun renderViewEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.ShowSnackbar -> {
                Snackbar.make(fab, event.string, event.length)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun load() {
        viewModel.dispatch(MainViewAction.Load)
    }

    private fun injectActivity() {
        App.appComponent.inject(this)
    }
}

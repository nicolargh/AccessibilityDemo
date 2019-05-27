package com.nicolag.accessibilitydemo.ui.view.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.App
import com.nicolag.accessibilitydemo.model.event.home.HomeViewEvent
import com.nicolag.accessibilitydemo.model.state.home.HomeViewState
import com.nicolag.accessibilitydemo.model.viewmodel.home.HomeViewModel
import com.nicolag.accessibilitydemo.ui.action.home.HomeViewAction
import com.nicolag.accessibilitydemo.ui.action.home.HomeViewClick
import com.nicolag.accessibilitydemo.ui.view.MainActivity
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    private val viewModel: HomeViewModel by lazy {
        viewModelFactory.getViewModel(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectFragment()
        observeViewModel()
        load()
    }

    private fun injectFragment() {
        App.appComponent.inject(this)
    }

    private fun observeViewModel() {
        viewModel.state().observe(this, Observer<HomeViewState> { state ->
            state?.let { renderViewState(it) }
        })
        viewModel.event().observe(this, Observer<HomeViewEvent> {
            renderViewEvent(it)
        })
    }

    private fun renderViewState(state: HomeViewState) {
        welcomeTextView.text = state.welcomeText
        MainActivity.getFabView(this).setOnClickListener {
            viewModel.dispatch(HomeViewAction.Click(HomeViewClick.Fab))
        }
    }

    private fun renderViewEvent(event: HomeViewEvent?) {
        when (event) {
            is HomeViewEvent.ShowSnackbar -> {
                Snackbar.make(MainActivity.getFabView(this), event.string, event.length)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun load() {
        viewModel.dispatch(HomeViewAction.Load)
    }
}
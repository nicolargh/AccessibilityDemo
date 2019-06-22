package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.list_view_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragment() {

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).visibility = View.GONE
        button.setOnClickListener {
            Toast.makeText(context, R.string.toast_text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search_cross -> openSearchResults(false)
            R.id.action_search_tick -> openSearchResults(true)
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openSearchResults(hideBackgroundContents: Boolean): Boolean {
        if (dismissIfOpen())
            return true
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.searchResultsFragmentContainer, SearchResultsFragment()).addToBackStack(null)
            .commit()

        if (hideBackgroundContents)
            backgroundView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS

        return true
    }

    private fun dismissIfOpen(): Boolean {
        val fragmentManager = requireActivity().supportFragmentManager
        return if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            dimmer.visibility = View.GONE

            // only has effect if background view was previously hidden for accessibility
            backgroundView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
            true
        } else {
            dimmer.visibility = View.VISIBLE
            false
        }
    }

    class SearchResultsFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.list_view_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            listView.adapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                (1..6).map { requireContext().getString(R.string.search_result, it) }.toTypedArray()
            )
        }
    }
}
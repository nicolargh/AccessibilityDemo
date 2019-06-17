package com.nicolag.accessibilitydemo.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.rating_bar_fragment.*

class RatingBarFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.rating_bar_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).visibility = View.GONE

        inflateView(true)
        inflateView(false)
    }

    private fun inflateView(isFirst: Boolean) {
        if (isFirst) inflateRating1() else inflateRating2()

        (if (isFirst) textView1 else textView2).text = requireContext().getString(R.string.rate_me)

        (if (isFirst) button1 else button2).apply {
            isEnabled = false
            setOnClickListener {
                isEnabled = false
                (if (isFirst) ratingBar1 else ratingBar2).isEnabled = true
                (if (isFirst) ratingBar1 else ratingBar2).rating = 0f
                (if (isFirst) textView1 else textView2).text = requireContext().getString(R.string.rate_me)
            }
        }
    }

    private fun inflateRating1() {
        ratingBar1.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            if (rating > 0) {
                ratingBar1.isEnabled = false
                textView1.text = requireContext().getString(R.string.rating_thanks, rating.toString())
                button1.isEnabled = true
            }
        }
    }

    private fun inflateRating2() {
        ratingBar2.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            // if not using a keyboard or talkback, accept rating
            // otherwise, update the content description
            if (!ratingBar2HasFocus() && rating > 0) {
                ratingBar2.isEnabled = false
                textView2.text = requireContext().getString(R.string.rating_thanks, rating.toString())
                button2.isEnabled = true
            }
            ratingBar2.contentDescription =
                resources.getString(R.string.rating_content_description, rating.toString(), ratingBar2.numStars)
        }

        ratingBar2.setOnClickListener {
            // if using a keyboard or talkback, accept rating on click
            if (ratingBar2HasFocus() && ratingBar2.rating > 0) {
                ratingBar2.isEnabled = false
                textView2.text = requireContext().getString(R.string.rating_thanks, ratingBar2.rating.toString())
                button2.isEnabled = true
            }
        }
    }

    private fun ratingBar2HasFocus(): Boolean {
        // True if rating bar has either keyboard or talkback accessibility focus
        return if (Build.VERSION.SDK_INT >= 21) {
            ratingBar2.hasFocus() || ratingBar2.isAccessibilityFocused
        } else {
            ratingBar2.hasFocus() // isAccessibilityFocused is API 21 and above
        }
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}
package com.nicolag.accessibilitydemo.ui.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.format.DateUtils
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.other_text_fragment.*

class OtherTextFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.other_text_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).visibility = View.GONE
        setupTextView1()
        setupTextView2()
        setupTextView3()
        setupTextView4()
        setupAddButtons()
    }

    private fun setupTextView1() {
        val text = "This is some text <b>that</b> <i>has</i> <u>some</u> <b><i><u>formatting</u></i></b>!"
        textView1.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(text)
    }

    private fun setupTextView2() {
        val tick = resources.getDrawable(R.drawable.ic_tick)
        tick.setBounds(0, 0, tick.intrinsicWidth, tick.intrinsicHeight)
        val span = ImageSpan(tick, ImageSpan.ALIGN_BASELINE)

        val text = "This text has some important information in an inline icon "
        val ss = SpannableString(text)
        ss.setSpan(span, text.length-1, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        textView2.text = ss
        textView2.contentDescription = "$text, ${requireContext().getString(R.string.tick_content_description)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupTextView3() {
        val minsAgo = 3
        val text = "This timestamp of ${minsAgo}m needs to be overridden"
        val timestamp = requireContext().getString(R.string.timestamp_mins_ago, minsAgo)
        val timestampContentDescription = requireContext().getString(R.string.timestamp_mins_ago_long, minsAgo)

        textView3.text = "$text$TIMESTAMP_SEPARATOR$timestamp" // concatenation here is okay - the timestamp is a suffix to the main content
        textView3.contentDescription = "$text$TIMESTAMP_SEPARATOR$timestampContentDescription"
    }

    @SuppressLint("SetTextI18n")
    private fun setupTextView4() {
        val time = 1554274800000 // 3rd of april, 2019
        val shortDate = DateUtils.formatDateTime(context, time, NUMERIC_MONTH_DAY)
        val longDate = DateUtils.formatDateTime(context, time, FULL_MONTH_DAY)

        val text = "Dates like $shortDate often read like fractions"
        val timestamp = requireContext().getString(R.string.timestamp_full, shortDate)
        val timestampContentDescription = requireContext().getString(R.string.timestamp_full, longDate)

        textView4.text = "$text$TIMESTAMP_SEPARATOR$timestamp" // concatenation here is okay - the timestamp is a suffix to the main content
        textView4.contentDescription = "$text$TIMESTAMP_SEPARATOR$timestampContentDescription"
    }

    private fun setupAddButtons() {
        val text = "Some words, if in all caps, read like acronyms"
        addText.text = text

        val addDescription = requireContext().getString(R.string.add_button)
        add1.text = addDescription
        add1.isAllCaps = true // can also be set in xml

        add2.text = addDescription
        add2.isAllCaps = true // can also be set in xml
        add2.contentDescription = addDescription
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    companion object {
        private const val TIMESTAMP_SEPARATOR = " - "
        private const val NUMERIC_MONTH_DAY = DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NUMERIC_DATE or DateUtils.FORMAT_NO_YEAR
        private const val FULL_MONTH_DAY = DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NO_YEAR
    }
}
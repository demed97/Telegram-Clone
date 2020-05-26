package com.android.dan.telegram.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.android.dan.telegram.MainActivity
import com.android.dan.telegram.R
import com.android.dan.telegram.utilits.APP_ACTIVITY
import com.android.dan.telegram.utilits.hideKeyboard


open class BaseChangeFragment (layout: Int): Fragment(layout) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingsConfirmChange -> change()
        }
        return true
    }

    open fun change() {

    }
}

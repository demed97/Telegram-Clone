package com.android.dan.telegram.ui.fragments

import com.android.dan.telegram.R
import com.android.dan.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settingsInputBio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settingsInputBio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBio)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    USER.bio = newBio
                    fragmentManager?.popBackStack()
                }
            }
    }
}

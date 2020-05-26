package com.android.dan.telegram.ui.fragments

import com.android.dan.telegram.R
import com.android.dan.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*


class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            settingsInputName.setText(fullnameList[0])
            settingsInputSurname.setText(fullnameList[1])
        } else settingsInputName.setText(fullnameList[0])
    }

    override fun change() {
        val name = settingsInputName.text.toString()
        val surname = settingsInputSurname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullName = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME).setValue(fullName)
                .addOnCompleteListener {
                if(it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    USER.fullname = fullName
                    APP_ACTIVITY.mAppDrawer.updateHeader()
                    fragmentManager?.popBackStack()
                }
            }
        }
    }
}

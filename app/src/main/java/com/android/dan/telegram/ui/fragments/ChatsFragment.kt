package com.android.dan.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.android.dan.telegram.R
import com.android.dan.telegram.utilits.APP_ACTIVITY


class ChatsFragment : Fragment(R.layout.fragment_chats) {



    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Chats"

    }
}

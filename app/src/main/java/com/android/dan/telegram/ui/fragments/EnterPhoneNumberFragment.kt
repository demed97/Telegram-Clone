package com.android.dan.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.android.dan.telegram.MainActivity

import com.android.dan.telegram.R
import com.android.dan.telegram.activities.RegisterActivity
import com.android.dan.telegram.utilits.AUTH
import com.android.dan.telegram.utilits.replaceActivity
import com.android.dan.telegram.utilits.replaceFragment
import com.android.dan.telegram.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Welcome")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
        }
        registerBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (registerInputPhoneNumber.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
//            replaceFragment(EnterCodeFragment())
        }
    }

    private fun authUser() {
        mPhoneNumber = registerInputPhoneNumber.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )

    }
}

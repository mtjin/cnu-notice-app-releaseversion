package com.mtjin.cnunoticeapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseHelper {
    lateinit var user: FirebaseUser
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}
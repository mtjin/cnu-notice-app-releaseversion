package com.mtjin.cnunoticeapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseHelper {
    var user: FirebaseUser? = null
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}
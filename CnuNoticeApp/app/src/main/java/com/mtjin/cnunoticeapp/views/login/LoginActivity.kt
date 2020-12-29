package com.mtjin.cnunoticeapp.views.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityLoginBinding
import com.mtjin.cnunoticeapp.utils.FirebaseHelper
import com.mtjin.cnunoticeapp.views.notice.NoticeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    val viewModel: LoginViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInIntent: Intent
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        initGso()
        initEvent()
    }

    private fun initEvent() {
        btn_google_login.setOnClickListener {
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun initGso() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        signInIntent = googleSignInClient.signInIntent
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                showToast(getString(R.string.login_fail_text))
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        FirebaseHelper.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.login_success_text))
                    FirebaseHelper.user = FirebaseHelper.auth.currentUser
                    startActivity(Intent(this, NoticeActivity::class.java))
                } else {
                    showToast(getString(R.string.login_fail_text))
                }
            }
    }
}

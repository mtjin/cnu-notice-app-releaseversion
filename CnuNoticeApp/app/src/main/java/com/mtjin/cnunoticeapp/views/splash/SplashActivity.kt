package com.mtjin.cnunoticeapp.views.splash


import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.mtjin.cnunoticeapp.utils.SharedPrefManager
import com.mtjin.cnunoticeapp.utils.constants.APP_VERSION_NAME
import com.mtjin.cnunoticeapp.views.login.LoginActivity
import com.mtjin.cnunoticeapp.views.main.MainActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        auth = FirebaseAuth.getInstance()
        mFirebaseRemoteConfig.fetch(3600).addOnCompleteListener {
            if (it.isSuccessful) mFirebaseRemoteConfig.fetchAndActivate()
        }
        val remoteConfigAppVersion = mFirebaseRemoteConfig.getDouble(APP_VERSION_NAME)
        Log.d(this.javaClass.simpleName, "리모트 컨피그 앱버전 -> $remoteConfigAppVersion")
        if (remoteConfigAppVersion <= getAppVersionCode()) {
            if (auth.currentUser != null) {
                Toast.makeText(this, "자동 로그인", Toast.LENGTH_SHORT).show()
                SharedPrefManager(this).uuid = auth.currentUser!!.uid
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        } else { //앱 버전이 낮을경우 업데이트 필요
            AlertDialog.Builder(this).apply {
                setTitle("안내")
                setMessage("앱 업데이트가 필요합니다.")
                setPositiveButton(
                    "업데이트"
                ) { _, _ ->
                    val appUpdateIntent = Intent(Intent.ACTION_VIEW)
                    appUpdateIntent.data = Uri.parse("market://details?id=$packageName")
                    startActivity(appUpdateIntent)
                    finish()
                }
                this.show()
            }
        }
    }


    private fun getAppVersionCode(): Double {
        try {
            val pInfo: PackageInfo =
                this.packageManager.getPackageInfo(this.packageName, 0)
            Log.d(this.javaClass.simpleName, "앱버전 -> ${pInfo.versionName}")
            return pInfo.versionName.toDouble()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0.0
    }
}
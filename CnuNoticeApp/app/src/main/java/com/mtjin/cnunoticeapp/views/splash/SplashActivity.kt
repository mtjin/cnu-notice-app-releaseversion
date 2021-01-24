package com.mtjin.cnunoticeapp.views.splash


import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
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
        mFirebaseRemoteConfig.fetchAndActivate()
        val appVersion = mFirebaseRemoteConfig.getString(APP_VERSION_NAME)
        if (appVersion == getAppVersionCode()) {
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


    private fun getAppVersionCode(): String {
        try {
            val pInfo: PackageInfo =
                this.packageManager.getPackageInfo(this.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }
}
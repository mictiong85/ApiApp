package jp.techacademy.thion.maikeru.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.activity_web_view.webView
import kotlinx.android.synthetic.main.activity_web_view2.*
import kotlinx.android.synthetic.main.fragment_api.*


class WebViewActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)
        webView2.loadUrl((intent.getSerializableExtra(KEY_URL) as FavoriteShop).url)

    }


    companion object {
        private const val KEY_URL = "key_url"

        fun start(activity: Activity, favoriteShop: FavoriteShop) {
            activity.startActivity(Intent(activity, WebViewActivity2::class.java).putExtra(KEY_URL,favoriteShop))
        }

    }


}


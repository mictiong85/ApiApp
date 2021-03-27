package jp.techacademy.thion.maikeru.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.fragment_api.*


class WebViewActivity : AppCompatActivity() {

    private val items= mutableListOf<Shop>()
    var url:String=""
    var onClickAddFavorite:((Shop)->Unit)?=null
    var onClickDeleteFavorite:((Shop)->Unit)?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val shop1 = intent.getSerializableExtra(KEY_URL) as Shop
        val data = shop1
        val isFavorite = FavoriteShop.findBy(data.id) != null

        url=shop1.couponUrls.toString()
        url = if (shop1.couponUrls.sp.isNotEmpty()) shop1.couponUrls.sp else shop1.couponUrls.pc
        webView.loadUrl(url)

        val shop2 = intent.getSerializableExtra(KEY_URL) as FavoriteShop
        val data2 = shop2
        //val isFavorite = FavoriteShop.findBy(data.id) != null

        webView.loadUrl(shop2.url)

        if(isFavorite){
            button1.text="削除"
        }else{
            button1.text="登録"
        }

        button1.setOnClickListener{v->
            if(isFavorite){
                onClickDeleteFavorite?.invoke(data)
            }else{
                onClickAddFavorite?.invoke(data)
            }

        }

    }


    companion object {
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, shop: Shop) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL,shop))
        }

        fun start1(activity: Activity, favoriteShop: FavoriteShop.Companion) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL,favoriteShop))
        }
    }


}



package jp.techacademy.thion.maikeru.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.fragment_api.*


class WebViewActivity : AppCompatActivity() {

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView.loadUrl(intent.getStringExtra(KEY_URL).toString())

    }
*/
/*
    companion object {
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, url: String) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL, url))
        }
    }*/


    private val items= mutableListOf<Shop>()
    var url:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view) //getIntextra  ....serializable no torigata ....?...
        //from url , coupoun nの中のpc ,cp, get url//

        url=intent.getSerializableExtra(KEY_URL).toString()
        val shop1=intent.getSerializableExtra(KEY_URL)
        url=shop1.id

        url = if (url.couponUrls.sp.isNotEmpty()) url.couponUrls.sp else url.couponUrls.pc


        webView.loadUrl(intent.getSerializableExtra(KEY_URL).toString())

    }

    val data=items[shop.position]
    val isFavorite=FavoriteShop.findBy(data.id)!=null
    var onClickAddFavorite:((Shop)->Unit)?=null

    var onClickDeleteFavorite:((Shop)->Unit)?=null

    val favoriteImageView: ImageView =view.findViewById(R.id.button1)

    //button....onclick etc, onclickaddafavorite.....
    private fun getFavoriteInfo(){
        favoriteImageView.apply{
            setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)
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
    }
}
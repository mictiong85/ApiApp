package jp.techacademy.thion.maikeru.apiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import okhttp3.internal.notify

class ApiAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items= mutableListOf<Shop>()

    var onClickAddFavorite:((Shop)->Unit)?=null

    var onClickDeleteFavorite:((Shop)->Unit)?=null

    var onClickItem1:((Shop)->Unit)?=null

    fun refresh(list:List<Shop>){
/*        items.apply{
            clear()
            addAll(list)
        }
        notifyDataSetChanged()*/
        update(list,false)
    }
    fun add (list:List<Shop>){
        update(list,true)
    }

    fun update(list:List<Shop>,isAdd:Boolean){
        items.apply {
            if(!isAdd){
                clear()
            }
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ApiItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_favorite,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ApiItemViewHolder){
            updateApiItemViewHolder(holder,position)
        }
    }

    class ApiItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val rootView:ConstraintLayout=view.findViewById(R.id.rootView)
        val nameTextView:TextView=view.findViewById(R.id.nameTextView)
        val imageView:ImageView=view.findViewById(R.id.imageView)
        val favoriteImageView:ImageView=view.findViewById(R.id.favoriteImageView)
        val addressTextView:TextView=view.findViewById(R.id.addressTextView)

    }

    private fun updateApiItemViewHolder(holder:ApiItemViewHolder,position: Int){
        val data=items[position]
        val isFavorite=FavoriteShop.findBy(data.id)!=null
        holder.apply{
            rootView.apply{
                setBackgroundColor(ContextCompat.getColor(context,
                if(position%2==0)android.R.color.white else android.R.color.darker_gray))
                setOnClickListener {
                    onClickItem1?.invoke(data)
                }
            }
            nameTextView.text=data.name
            addressTextView.text=data.address
            Picasso.get().load(data.logoImage).into(imageView)
            favoriteImageView.apply{
                setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)
                setOnClickListener{
                    if(isFavorite){
                        onClickDeleteFavorite?.invoke(data)
                    }else{
                        onClickAddFavorite?.invoke(data)
                    }
                    notifyItemChanged(position)
                }
            }
        }

    }

}
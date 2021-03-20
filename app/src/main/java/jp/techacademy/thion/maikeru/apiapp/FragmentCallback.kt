package jp.techacademy.thion.maikeru.apiapp

interface FragmentCallback {
    fun onClickItem(url: String)
    fun onAddFavorite(shop:Shop)
    fun onDeleteFavorite(id:String)
}
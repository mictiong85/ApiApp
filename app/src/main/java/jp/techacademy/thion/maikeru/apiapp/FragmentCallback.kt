package jp.techacademy.thion.maikeru.apiapp

interface FragmentCallback {
    fun onAddFavorite(shop:Shop)
    fun onDeleteFavorite(id:String)
    fun onClickItem1(shop:Shop)
}
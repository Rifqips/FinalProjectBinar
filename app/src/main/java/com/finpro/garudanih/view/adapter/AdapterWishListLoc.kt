package com.finpro.garudanih.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemWishlistBinding
import com.finpro.garudanih.view.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.view.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.view.wishlist.WishPesawatDaoLoc
import kotlinx.coroutines.*

@Suppress("DeferredResultUnused", "RemoveEmptyClassBody", "MemberVisibilityCanBePrivate", "unused")
class AdapterWishListLoc(): RecyclerView.Adapter<AdapterWishListLoc.ViewHolder>() {

    private lateinit var context : Context
    var databaseWishPesawatLoc: DatabaseWishPesawatLoc? = null
    private lateinit var daolocal : WishPesawatDaoLoc
    private var Wishlist : List<DataWishPesawatLoc> = emptyList()
    private var database: DatabaseWishPesawatLoc? = null

    class ViewHolder(val binding: ItemWishlistBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        database = DatabaseWishPesawatLoc.getInstance(parent.context)
        if(database != null){
            daolocal = database!!.WishDao()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = Wishlist[position].destination
        holder.binding.txtKotaAsal.text = Wishlist[position].departure
        holder.binding.txtJadwal.text = Wishlist[position].takeOff
        holder.binding.txtHarga.text = "Rp" + Wishlist[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = Wishlist[position].totalChair.toString()
        holder.binding.txtClass.text = Wishlist[position].classX
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListWishlist(list: List<DataWishPesawatLoc>){
        this.Wishlist = list
        notifyDataSetChanged()
    }

    fun getWishlist(position: Int): DataWishPesawatLoc = Wishlist[position]

    override fun getItemCount(): Int {
        return  Wishlist.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    interface OnAdapterListener {
        fun onDelete(wishlist: DataWishPesawatLoc)
    }

    fun deleteDomestic(wishInternational: DataWishPesawatLoc, position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            daolocal.deleteWishLoc(wishInternational)
        }
        notifyItemChanged(position)
    }
}
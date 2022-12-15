package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishlistActivity
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterWishListLoc(val Wishlist : List<DataWishPesawatLoc>): RecyclerView.Adapter<AdapterWishListLoc.ViewHolder>() {

    var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null
    class ViewHolder (val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = Wishlist[position].destination
        holder.binding.txtKotaAsal.text = Wishlist[position].departure
        holder.binding.txtJadwal.text = Wishlist[position].takeOff
        holder.binding.txtHarga.text = "Rp"+Wishlist[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = Wishlist[position].totalChair.toString()
        holder.binding.txtClass.text = Wishlist[position].classX

        holder.binding.delete.setOnClickListener {
            databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(it.context)
            GlobalScope.async {
                val delete = databaseWishPesawatLoc?.WishDao()?.deleteWishLoc(Wishlist[position])
                (holder.itemView.context as WishlistActivity).runOnUiThread {
                    Toast.makeText(it.context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    (holder.itemView.context as WishlistActivity).getWishlistLocal()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return Wishlist.size
    }
}
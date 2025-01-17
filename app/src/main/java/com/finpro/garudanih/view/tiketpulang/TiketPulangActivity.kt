@file:Suppress("NestedLambdaShadowedImplicitParameter", "NestedLambdaShadowedImplicitParameter",
    "NestedLambdaShadowedImplicitParameter", "NestedLambdaShadowedImplicitParameter",
    "NestedLambdaShadowedImplicitParameter", "NestedLambdaShadowedImplicitParameter",
    "NestedLambdaShadowedImplicitParameter", "ReplaceGetOrSet", "ReplaceGetOrSet"
)

package com.finpro.garudanih.view.tiketpulang

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finpro.garudanih.view.adapter.AdapterPP
import com.finpro.garudanih.databinding.ActivityTiketPulangBinding
import com.finpro.garudanih.view.detils.DetailPulangActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketPulangActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketPulangBinding
    private lateinit var viewModelUser : ViewModelUser
    lateinit var authViewModel : AuthViewModel
    private lateinit var adapterTiketPulang: AdapterPP
    private var tokenUser : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketPulangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModelUser = ViewModelProvider(this).get(ViewModelUser::class.java)
        authViewModel.getToken().observe(this){token->
            if (token != null){
                tokenUser = "Bearer $token"
            }
        }
        setTiketPulang()


    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setTiketPulang(){
        val idTiketPergi = intent.getIntExtra("id",0)
        binding.txtidpul.text = "ID Tiket : $idTiketPergi"
        binding.txtidpul.isGone = true


//        adapterTiketPulang = AdapterPP {
//            val pindah = Intent(this, DetailInternasionalActivity::class.java)
//            pindah.putExtra("getidpp", it)
//            pindah.putExtra(DetailInternasionalActivity.EXTRA_ID, it.id)
//            startActivity(pindah)
//        }
        val idTiket = intent.getIntExtra("id",0)
        viewModelUser.getDetailByIdObserve().observe(this){
            if (it != null){

                binding.rvTiketPulang.layoutManager = GridLayoutManager(this,2)
                adapterTiketPulang = AdapterPP(it.data.returnTicket)
                binding.rvTiketPulang.adapter = adapterTiketPulang
                adapterTiketPulang.notifyDataSetChanged()
                adapterTiketPulang.onClick={
                    val intent = Intent(this,DetailPulangActivity::class.java)
                    intent.putExtra("getId",idTiketPergi)
                    intent.putExtra("id", it.id)
                    intent.putExtra("destinasi", it.destination)
                    intent.putExtra("departure", it.departure)
                    intent.putExtra("jadwal", it.takeOff)
                    intent.putExtra("harga", it.price)
                    intent.putExtra("totalchair", it.totalChair)
                    intent.putExtra("class", it.classX)

                    startActivity(intent)
                }

//                binding.rvTiketPulang.layoutManager= LinearLayoutManager(this,
//                    LinearLayoutManager.HORIZONTAL,false)
//                adapterTiketPulang.setListTiket(it.data.returnTicket)
//                binding.rvTiketPulang.adapter = adapterTiketPulang
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelUser.callDetailById(tokenUser,idTiket)
    }
}


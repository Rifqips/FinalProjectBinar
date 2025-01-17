@file:Suppress("unused")

package com.finpro.garudanih.view.detils

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finpro.garudanih.view.adapter.AdapterLokal
import com.finpro.garudanih.databinding.ActivityTiketLokalBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("MemberVisibilityCanBePrivate", "ReplaceGetOrSet")
@AndroidEntryPoint
class TiketLokalActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketLokalBinding
    lateinit var tiketLokalAdapter : AdapterLokal
    lateinit var viewModelListTiket : TiketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketLokalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTiketLokal()
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
    }

    private fun setTiketLokal(){
        val viewModelListTiket = ViewModelProvider(this).get(TiketViewModel::class.java)
        viewModelListTiket.getLdTiket().observe(this) {
            binding.loader.visibility = View.VISIBLE
            binding.loader.startShimmer()
            if (it != null) {
                binding.loader.visibility = View.GONE
                binding.loader.stopShimmer()
                binding.rvAllLocal.layoutManager = GridLayoutManager(this,2)
                tiketLokalAdapter = AdapterLokal(it.data.tickets)
                binding.rvAllLocal.adapter = tiketLokalAdapter
                Toast.makeText(this, "Data Tampil", Toast.LENGTH_SHORT).show()
            } else {
                binding.loader.visibility = View.VISIBLE
                binding.loader.startShimmer()
                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelListTiket.CallApiTiket()
    }
}
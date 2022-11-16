package com.finpro.garudanih.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityOnBoardingDuaBinding
import com.finpro.garudanih.databinding.ActivityOnBoardingSatuBinding

class OnBoardingSatu : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingSatuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingSatuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNext.setOnClickListener {
            val toOnboardSec = Intent(this,OnBoardingDua::class.java)
            startActivity(toOnboardSec)
        }
    }
}
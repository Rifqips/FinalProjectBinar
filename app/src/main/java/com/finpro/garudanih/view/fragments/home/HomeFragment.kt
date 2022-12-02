package com.finpro.garudanih.view.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.adapter.AdapterListPesawat
import com.finpro.garudanih.adapter.AdapterTiket
import com.finpro.garudanih.adapter.ViewPagerFragmentAdapter
import com.finpro.garudanih.databinding.FragmentHomeBinding
import com.finpro.garudanih.model.ListInternasional
import com.finpro.garudanih.model.ListPesawat
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.profile.ProfileActivity
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeOne
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeThree
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeTwo
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.currentCoroutineContext
import me.relex.circleindicator.CircleIndicator3
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val data = mutableListOf<String>()
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    lateinit var viewModelListTiket : TiketViewModel
    lateinit var authViewModel : AuthViewModel
    lateinit var userViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfile()
        setUsername()

        binding.ivUser.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

        val listInt = arrayListOf(
            ListInternasional("China",2000000,"16 Agustus","24/100",R.drawable.ic_logogn,"pending"),
            ListInternasional("Malaysia",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListInternasional("Thailand",2000000,"16 Agustus","24/100",R.drawable.jakarta,"pending"),
            ListInternasional("Singapura",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),

            )
        binding.rvInternational.adapter = AdapterInternasional(listInt)
        binding.rvInternational.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//
//        val list = arrayListOf(
//            ListPesawat("China",2000000,"16 Agustus","24/100",R.drawable.ic_logogn,"pending","Economy"),
//            ListPesawat("Malaysia",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending","Economy"),
//            ListPesawat("Thailand",2000000,"16 Agustus","24/100",R.drawable.jakarta,"pending","Economy"),
//            ListPesawat("Singapura",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending","Economy"),
//        )
//        binding.rvLocal.adapter = AdapterListPesawat(list)
//        binding.rvLocal.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)


        callListTiketLocal()
        bannerHome()

    }

    fun bannerHome(){
        castView()


        fragmentList.add(FragmentVpHomeOne())
        fragmentList.add(FragmentVpHomeTwo())
        fragmentList.add(FragmentVpHomeThree())

        viewPager.adapter = ViewPagerFragmentAdapter(requireActivity(),fragmentList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        indicator.setViewPager(viewPager)
    }
    private fun castView() {
        viewPager = binding.viewPagerHome
        indicator = binding.indicatorBanner
    }
    private fun addToList() {
        for (item in 1..3) {
            data.add("item $item")
        }
    }


    fun callListTiketLocal(){
        viewModelListTiket = ViewModelProvider(requireActivity()).get(TiketViewModel::class.java)
        viewModelListTiket.getAllTiket().observe(requireActivity(),{
            if (it != null) {
                binding.rvLocal.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                val adapter = AdapterTiket(it.data.tickets)
                binding.rvLocal.adapter = adapter
            }
        })
        viewModelListTiket.callApiListTiket()
    }
    private fun getProfile(){
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(requireActivity()){
            if (it != null){
                userViewModel.currentUser("Bearer $it")
            }else{
                Log.d("TOKEN","Token Null")
            }
        }
    }
    private fun setUsername(){
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        userViewModel.getCurrentObserve().observe(requireActivity()){
            if (it != null){
                binding.txtUsername.text = ("Hello, "+it.name)
            }else{
                Log.d("PROFILE","Profile Null")
            }
        }
        authViewModel.getUser().observe(requireActivity()){
            if (it != null){
                binding.txtUsername.text = ("Hello, "+it)
            }
        }
    }

}
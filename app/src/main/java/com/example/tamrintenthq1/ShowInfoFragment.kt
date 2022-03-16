package com.example.tamrintenthq1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tamrintenthq1.databinding.FragmentShowInfoBinding

var sharedPreferences: SharedPreferences?=null

class ShowInfoFragment : Fragment() {
    lateinit var binding: FragmentShowInfoBinding

   val args:ShowInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentShowInfoBinding.inflate(layoutInflater,container,false)
        sharedPreferences= this.activity?.getSharedPreferences("Info", Context.MODE_PRIVATE)
        getInfo()
        saveInfo()
        return binding.root
    }
    private fun getInfo(){
        binding.tvFullName.text=args.fullname
        binding.tvUsername.text=args.username
        binding.tvEmail.text=args.email
        binding.tvPassword.text=args.password
        binding.tvGenderSf.text=args.gender

    }
    private fun saveInfo(){
        val editor= sharedPreferences?.edit()
        binding.btnSave.setOnClickListener {
            editor?.putString(FullName, binding.tvFullName.text.toString())
            editor?.putString(UserName, binding.tvUsername.text.toString())
            editor?.putString(Email, binding.tvEmail.text.toString())
            editor?.putString(Password, binding.tvPassword.text.toString())
            editor?.putString(Gender, binding.tvGenderSf.text.toString())
            editor?.apply()
            findNavController().navigate(R.id.action_showInfoFragment_to_registerFragment)
        }
    }

}
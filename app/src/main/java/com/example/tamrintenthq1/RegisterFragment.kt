package com.example.tamrintenthq1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.tamrintenthq1.databinding.FragmentRegisterBinding


const val FullName="fullName"
const val UserName="userName"
const val Email="email"
const val Password="password"
const val Gender="gender"

class RegisterFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding
    var listOfInfo= mutableListOf<EditText>()
    var gender=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegisterBinding.inflate(layoutInflater,container,false)
        sharedPreferences = this.activity?.getSharedPreferences("Info", Context.MODE_PRIVATE)
        initView()
        return binding.root
    }
    private fun initView() {
        listOfInfo.add(binding.evFullName)
        listOfInfo.add(binding.evUsername)
        listOfInfo.add(binding.evEmail)
        listOfInfo.add(binding.evPassword)
        listOfInfo.add(binding.evReTypePassword)
        if (sharedPreferences?.getString(FullName,"")!="") {
            setInfo()
            register()
        }else {
            register()
        }
    }

    private fun setInfo() {
        binding.evFullName.setText(sharedPreferences?.getString(FullName,""))
        binding.evUsername.setText(sharedPreferences?.getString(UserName,""))
        binding.evEmail.setText(sharedPreferences?.getString(Email,""))
        binding.evPassword.setText(sharedPreferences?.getString(Password,""))
        binding.evReTypePassword.setText(sharedPreferences?.getString(Password,""))
        when(sharedPreferences?.getString(Gender,"")){
            "Female"->binding.radioBtnFemale.isChecked=true
            "Male"->binding.radioBtnMale.isChecked=true
        }
    }

    private fun register() {
        binding.btnRegister.setOnClickListener {
            if(isCheck()){
               var fullName=binding.evFullName.text.toString()
                var userName=binding.evUsername.text.toString()
                var email=binding.evEmail.text.toString()
                var password=binding.evPassword.text.toString()
                var gender=gender
                val action=RegisterFragmentDirections.actionRegisterFragmentToShowInfoFragment(fullName,userName,email,password,gender)
                findNavController().navigate(action)
            }
        }
    }

    private fun isCheck():Boolean{
        var result=true
        when {
            binding.radioBtnFemale.isChecked -> gender="Female"
            binding.radioBtnMale.isChecked -> gender="Male"
            else -> {
                Toast.makeText(context, "Enter Gender!", Toast.LENGTH_SHORT).show()
                result=false
            }
        }
        listOfInfo.forEach{
            if(it.text.isBlank()) {
                it.error ="Can not be empty!"
                result= false
            }
        }

        if(binding.evPassword.text.toString()!= binding.evReTypePassword.text.toString()){
            binding.evReTypePassword.error="Password is not match!"
            result=false
        }
        return result
    }

}
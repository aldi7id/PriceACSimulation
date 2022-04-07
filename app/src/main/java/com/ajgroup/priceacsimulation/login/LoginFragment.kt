package com.ajgroup.priceacsimulation.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ajgroup.priceacsimulation.R
import com.ajgroup.priceacsimulation.database.RegisterDatabase
import com.ajgroup.priceacsimulation.database.RegisterRepository
import com.ajgroup.priceacsimulation.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel
    private val sharedPrefFile = "kotlinsharedpreference"
    var sharedPreferences:SharedPreferences? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val userNameShared = sharedPreferences?.getString("user_key","")
        if (userNameShared != ""){
            navigateUserDetails()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        //return binding.root
//        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_login, container, false
//        )
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.myLoginViewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        })
        loginViewModel.errortoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername .observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "User doesnt exist,please Register!", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoastInvalidPassword()
            }
        })

        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        })

        return binding.root
    }
    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }
    private fun navigateUserDetails() {
        Log.i("MYTAG","insidisplayUsersList")
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putString("user_key", binding.userNameTextField.text.toString())
        editor.apply()
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }
}
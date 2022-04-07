package com.ajgroup.priceacsimulation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajgroup.priceacsimulation.R
import com.ajgroup.priceacsimulation.database.RegisterDatabase
import com.ajgroup.priceacsimulation.database.RegisterRepository
import com.ajgroup.priceacsimulation.databinding.FragmentHomeBinding
import com.ajgroup.priceacsimulation.databinding.FragmentRegisterBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = HomeViewModelFactory(repository, application)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.homeLayout = homeViewModel

        binding.lifecycleOwner = this

        homeViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished== true) {
                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                NavHostFragment.findNavController(this).navigate(action)
                homeViewModel.doneNavigating()
            }
        })

        initRecyclerView()

        return binding.root
    }
    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        homeViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }
}
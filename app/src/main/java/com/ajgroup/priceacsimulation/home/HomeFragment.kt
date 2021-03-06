package com.ajgroup.priceacsimulation.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajgroup.priceacsimulation.database.AcEntity
import com.ajgroup.priceacsimulation.database.AcRepository
import com.ajgroup.priceacsimulation.database.RegisterDatabase
import com.ajgroup.priceacsimulation.databinding.CustomDialogBinding
import com.ajgroup.priceacsimulation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_home, container, false
//        )
        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).acDao

        val repository = AcRepository(dao)

        val factory = HomeViewModelFactory(repository, application)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.homeLayout = homeViewModel

        binding.lifecycleOwner = this

//        homeViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
//            if (hasFinished== true) {
//                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
//                NavHostFragment.findNavController(this).navigate(action)
//                homeViewModel.doneNavigating()
//            }
//        })

        initRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            val bindingDialog: CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val builder = AlertDialog.Builder(context)
            builder.setView(bindingDialog.root)
            val dialog = builder.create()
            bindingDialog.btnSave.setOnClickListener {
                val ac = AcEntity(
                    null,
                    bindingDialog.etTitle.text.toString(),
                    bindingDialog.etMerk.text.toString(),
                    bindingDialog.etUkuran.text.toString(),
                    bindingDialog.etBuatan.text.toString(),
                    bindingDialog.etHarga.text.toString()
                )
                homeViewModel.inserData(ac)
                Toast.makeText(context, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        homeViewModel.acs.observe(viewLifecycleOwner, Observer {
            binding.usersRecyclerView.adapter = AcAdapter(it,{ acEntity ->
                val bindingDialog: CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(context)
                builder.setView(bindingDialog.root)
                val dialog = builder.create()
                bindingDialog.etTitle.setText(acEntity.title)
                bindingDialog.etMerk.setText(acEntity.merk)
                bindingDialog.etUkuran.setText(acEntity.ukuran)
                bindingDialog.etBuatan.setText(acEntity.buatan)
                bindingDialog.etHarga.setText(acEntity.harga)
                bindingDialog.btnSave.setOnClickListener {
                    val ac = AcEntity(
                        acEntity.userId,
                        bindingDialog.etTitle.text.toString(),
                        bindingDialog.etMerk.text.toString(),
                        bindingDialog.etUkuran.text.toString(),
                        bindingDialog.etBuatan.text.toString(),
                        bindingDialog.etHarga.text.toString()
                    )
                    homeViewModel.inserData(ac)
                    Toast.makeText(context, "Edit Data Berhasil", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dialog.show()
            },{
                val dialogWithButton = AlertDialog.Builder(context)
                dialogWithButton.setTitle("Apakah Anda Yakin Ingin Menghapus?")

                dialogWithButton.setCancelable(false)
                dialogWithButton.setPositiveButton("Yakin",
                    DialogInterface.OnClickListener {
                            dialogInterface, i ->
                        homeViewModel.deleteData(it)
                        Toast.makeText(context, "Data Berhasil di Delete", Toast.LENGTH_SHORT).show()
                    })
                dialogWithButton.setNeutralButton("Batal",
                    DialogInterface.OnClickListener {
                            dialogInterface, i ->
                        Toast.makeText(context, "Batal Menghapus Data", Toast.LENGTH_SHORT).show() })
                 dialogWithButton.show()
                //Toast.makeText(context, "Delete Data Berhasil", Toast.LENGTH_SHORT).show()
            })
        })

    }
}
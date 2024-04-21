package com.example.a160421105_uts_project_hobby.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160421105_uts_project_hobby.R
import com.example.a160421105_uts_project_hobby.databinding.FragmentDetailBinding
import com.example.a160421105_uts_project_hobby.databinding.FragmentProfileBinding
import com.example.a160421105_uts_project_hobby.model.Global
import com.example.a160421105_uts_project_hobby.viewmodel.ProfileViewModel
import java.util.logging.Logger.global

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return(binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Global.currentUser?.let {
            with(binding) {
                txtNamaDepan.setText(Global.currentUser?.namaDepan)
                txtNamaBelakang.setText(Global.currentUser?.namaBelakang)

                txtUsername.text = Global.currentUser?.username
                txtEmail.text= Global.currentUser?.email

                btnChangePass.setOnClickListener {
                    val oldPassword = txtOldPass.text.toString()
                    val newPassword = txtNewPassword.text.toString()
                    val newPassword2 = txtNewPassword2.text.toString()

                    if (oldPassword != Global.currentUser?.password) {
                        Toast.makeText(requireActivity(), "Password salah", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    if (newPassword.isBlank() || newPassword2.isBlank() || newPassword != newPassword2) {
                        Toast.makeText(requireActivity(), "Password baru tidak sama atau kosong !", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
                    viewModel.update(
                        Global.currentUser?.id.toString(),
                        Global.currentUser?.namaDepan.toString(),
                        Global.currentUser?.namaBelakang.toString(),
                        newPassword
                    )
                    Toast.makeText(requireActivity(), "Ganti Password Berhasil", Toast.LENGTH_SHORT).show()
                    observerViewModel()
                    txtOldPass.setText("")
                    txtNewPassword.setText("")
                    txtNewPassword2.setText("")

                }

                btnChangeName.setOnClickListener{
                    val oldPassword = txtOldPass.text.toString()
                    val newFirstName = txtNamaDepan.text.toString()
                    val newLastName = txtNamaBelakang.text.toString()

                    if (oldPassword != Global.currentUser?.password) {
                        Toast.makeText(requireActivity(), "Password Salah", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    if (newFirstName.isBlank() || newLastName.isBlank()) {
                        Toast.makeText(requireActivity(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
                    viewModel.update(
                        Global.currentUser?.id.toString(),
                        newFirstName,
                        newLastName,
                        Global.currentUser?.password.toString()
                    )
                    Toast.makeText(requireActivity(), "Ganti Nama Berhasil", Toast.LENGTH_SHORT).show()
                    observerViewModel()
                    txtOldPass.setText("")
                    txtNamaDepan.setText("")
                    txtNamaBelakang.setText("")
                    txtNewPassword.setText("")
                    txtNewPassword2.setText("")
                }
            }

        }


        binding.btnLogOut.setOnClickListener{
            Global.currentUser = null
            val intent = Intent(it.context, LoginActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    private fun observerViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer{
            Global.currentUser = it
            binding.txtNamaDepan.setText(Global.currentUser?.namaDepan)
            binding.txtNamaBelakang.setText(Global.currentUser?.namaBelakang)

        })
    }

}
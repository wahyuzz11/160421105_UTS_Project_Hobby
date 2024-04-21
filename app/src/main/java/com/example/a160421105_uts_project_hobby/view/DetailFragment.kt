package com.example.a160421105_uts_project_hobby.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160421105_uts_project_hobby.R
import com.example.a160421105_uts_project_hobby.databinding.FragmentDetailBinding
import com.example.a160421105_uts_project_hobby.databinding.FragmentHomeBinding
import com.example.a160421105_uts_project_hobby.model.Beritas
import com.example.a160421105_uts_project_hobby.model.detilBeritas
import com.example.a160421105_uts_project_hobby.viewmodel.DetailListViewModel
import com.example.a160421105_uts_project_hobby.viewmodel.HomeListViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailListViewModel
    private lateinit var binding:FragmentDetailBinding
    var index: Int = 0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val idBeritas = DetailFragmentArgs.fromBundle(requireArguments()).idBerita
            val penulis = DetailFragmentArgs.fromBundle(requireArguments()).penulis
            val judul = DetailFragmentArgs.fromBundle(requireArguments()).judul
            val imgUrl = DetailFragmentArgs.fromBundle(requireArguments()).imgUrl
            binding.txtJudul.setText(judul)
            binding.txtPenulis.setText(penulis)
            val picasso = Picasso.Builder(binding.root.context)
            picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
            picasso.build().load(imgUrl).into(binding.imgBerita)
            viewModel = ViewModelProvider(this).get(DetailListViewModel::class.java)
            viewModel.fetch(idBeritas)
            observeViewModel()
        }

    }

    private fun observeViewModel(){
        viewModel.detilLD.observe(viewLifecycleOwner,Observer{
            var detils = it
            binding.txtJudulParagraf.setText(detils[index].judulParagraf)
            binding.txtParagraf.setText(detils[index].paragraf)

            fun updateButtonState() {
                binding.btnPrev.isEnabled = index > 0
                binding.btnNext.isEnabled = index < detils.size - 1
            }


            binding.btnPrev.setOnClickListener {
                if (index > 0) {
                    index--
                    updateButtonState()
                    binding.txtJudulParagraf.setText(detils[index].judulParagraf)
                    binding.txtParagraf.setText(detils[index].paragraf)
                }
            }

            binding.btnNext.setOnClickListener {
                if (index < detils.size - 1) {
                    index++
                    updateButtonState()
                    binding.txtJudulParagraf.setText(detils[index].judulParagraf)
                    binding.txtParagraf.setText(detils[index].paragraf)
                }
            }


            updateButtonState()
        })


    }

}
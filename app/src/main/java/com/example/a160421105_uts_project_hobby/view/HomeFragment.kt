package com.example.a160421105_uts_project_hobby.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160421105_uts_project_hobby.R
import com.example.a160421105_uts_project_hobby.databinding.FragmentHomeBinding
import com.example.a160421105_uts_project_hobby.viewmodel.HomeListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel:HomeListViewModel
    private lateinit var binding:FragmentHomeBinding

    private val homeListAdapter = HomeListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = homeListAdapter

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }


    fun observeViewModel() {
        viewModel.berita.observe(viewLifecycleOwner, Observer{
            homeListAdapter.updateBeritaList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE


            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

            }
        })

        viewModel.beritaLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError?.visibility = View.VISIBLE

            }else{
                binding.txtError?.visibility = View.GONE
            }
        })
    }
}
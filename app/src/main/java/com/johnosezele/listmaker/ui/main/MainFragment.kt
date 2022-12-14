package com.johnosezele.listmaker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnosezele.listmaker.databinding.MainFragmentBinding
import com.johnosezele.listmaker.models.MainViewModel
import com.johnosezele.listmaker.models.MainViewModelFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    //Step1: data binding initiated. The property that holds view binding
    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
        MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
            ).get(MainViewModel::class.java)

        val recyclerViewAdapter =
            ListSelectionRecyclerViewAdapter(viewModel.lists)

        binding.listsRecyclerview.adapter = recyclerViewAdapter

        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }

}
package com.myanmarit.anew.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmarit.anew.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeAdapter = HomeAdapter()

        setupRecyclerView()
        observeViewModel()
    }
    fun setupRecyclerView(){
        Recycler.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

    }
    fun observeViewModel(){
        homeViewModel.getResult().observe(
            viewLifecycleOwner, Observer {

            }
        )
        homeViewModel.getLoading().observe(
            viewLifecycleOwner, Observer { isLoading ->
                loadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE

            }
        )
        homeViewModel.getErrorStatus().observe(
            viewLifecycleOwner, Observer { status ->
                if (status){
                    homeViewModel.getErrorMessage().observe(
                        viewLifecycleOwner, Observer {
                            errorMessage.text = it
                        }
                    )
                }
            }
        )
    }
override fun onResume(){
    super.onResume()
    homeViewModel.loadResult()
}
}
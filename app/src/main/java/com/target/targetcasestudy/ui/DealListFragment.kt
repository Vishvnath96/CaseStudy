package com.target.targetcasestudy.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.di.Injection
import com.target.targetcasestudy.viewmodel.DataViewModel


class DealListFragment : Fragment(),DealItemAdapter.ItemClickHandler  {

    private lateinit var adapter: DealItemAdapter
    private var recyclerView: RecyclerView? = null
    private lateinit var binding: FragmentDealListBinding


    companion object {
        const val TAG = "DealListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_deal_list,
            container,
            false
        )
        recyclerView = binding.recyclerView
        setUpUi()
        initObserver()
        return binding.root
    }


    private val viewModel: DataViewModel by lazy {
        ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        ).get(DataViewModel::class.java)
    }

    private fun setUpUi() {
      val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
      recyclerView?.layoutManager = mLayoutManager
      adapter = DealItemAdapter(this)
      recyclerView?.adapter = adapter



    }


    private fun initObserver() {
        viewModel.dataListLiveData.observe(viewLifecycleOwner, renderData)
        viewModel.viewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
    }

    //observers
    private val renderData = Observer<List<Products>> {
        Log.v(TAG, "data updated $it")
        adapter.updateData(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }

    override fun itemClick(item: Products) {
        val fragment = DealItemFragment.newInstance(item)
        fragmentManager?.beginTransaction()?.addToBackStack("DealListFragment")?.replace(R.id.container, fragment)?.commit()
    }


}

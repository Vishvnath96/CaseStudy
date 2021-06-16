package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.di.Injection
import com.target.targetcasestudy.viewmodel.DataViewModel
import com.target.targetcasestudy.viewmodel.DealItemFragmentViewModel


class DealItemFragment : Fragment() {

  private lateinit var binding: FragmentDealItemBinding
  private var item: Products? = null

  companion object {
    const val DATA_KEY = "DATA_KEY"

    fun newInstance(item: Products): DealItemFragment {
      val fragment = DealItemFragment()
      val bundle = Bundle()
      bundle.putParcelable(DATA_KEY, item)
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let { bundle ->
      item = bundle.getParcelable(DATA_KEY)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deal_item, container, false)
    binding.viewmodel = viewModel
    return binding.root
  }

  private val viewModel: DealItemFragmentViewModel by lazy {
    ViewModelProvider(
      this,
      object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
          return item?.let { DealItemFragmentViewModel(it) } as T
        }
      }
    ).get(DealItemFragmentViewModel::class.java)
  }


}

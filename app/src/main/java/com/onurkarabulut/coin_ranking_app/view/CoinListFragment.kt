package com.onurkarabulut.coin_ranking_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurkarabulut.coin_ranking_app.R
import com.onurkarabulut.coin_ranking_app.adapter.CoinListRecyclerAdapter
import com.onurkarabulut.coin_ranking_app.databinding.FragmentCoinListBinding
import com.onurkarabulut.coin_ranking_app.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.fragment_coin_list.*


class CoinListFragment : Fragment() {
    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CoinListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        viewModel.getDatasFromAPI()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefleftLayout.setOnRefreshListener {
            binding.coinListPB.visibility = View.VISIBLE
            binding.coinListRV.visibility = View.GONE
            viewModel.getDatasFromAPI()
            binding.swipeRefleftLayout.isRefreshing = false
        }
        getData()
    }


    private fun getData(){
        viewModel.coinList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.data?.let{
                it.coins?.let {
                    binding.coinListRV.visibility = View.VISIBLE
                    binding.coinListRV.layoutManager = LinearLayoutManager(context)
                    binding.coinListRV.adapter = CoinListRecyclerAdapter(it)
                }
            }
        })
        viewModel.coinLoading.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it){
                binding.coinListRV.visibility = View.GONE
                binding.coinListPB.visibility = View.VISIBLE
            }else{
                binding.coinListPB.visibility = View.GONE
            }
        })
    }
}
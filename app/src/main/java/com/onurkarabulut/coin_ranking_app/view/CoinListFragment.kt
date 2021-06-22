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
import com.onurkarabulut.coin_ranking_app.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.fragment_coin_list.*


class CoinListFragment : Fragment() {
    private lateinit var viewModel : CoinListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        viewModel.getDatasFromAPI()
        swipeRefleftLayout.setOnRefreshListener {
            coinListPB.visibility = View.VISIBLE
            coinListRV.visibility = View.GONE
            viewModel.getDatasFromAPI()
            swipeRefleftLayout.isRefreshing = false
        }
        viewModel.coinList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.data?.let{
                it.coins?.let {
                    coinListRV.visibility = View.VISIBLE
                    coinListRV.layoutManager = LinearLayoutManager(context)
                    coinListRV.adapter = CoinListRecyclerAdapter(it)
                }
            }
        })
        viewModel.coinLoading.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it){
                coinListRV.visibility = View.GONE
                coinListPB.visibility = View.VISIBLE
            }else{
                coinListPB.visibility = View.GONE
            }
        })
    }
}
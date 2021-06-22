package com.onurkarabulut.coin_ranking_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.onurkarabulut.coin_ranking_app.R
import com.onurkarabulut.coin_ranking_app.adapter.CoinDetailSparkAdapter
import com.onurkarabulut.coin_ranking_app.databinding.FragmentCoinDetailBinding
import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import com.onurkarabulut.coin_ranking_app.util.loadSvg
import com.onurkarabulut.coin_ranking_app.viewmodel.CoinDetailViewModel
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.fragment_coin_detail.*
import java.security.KeyStore
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class CoinDetailFragment : Fragment() {
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : CoinDetailSparkAdapter

    private lateinit var viewModel: CoinDetailViewModel
    private var coinId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coinId = CoinDetailFragmentArgs.fromBundle(it).id
        }
        viewModel = ViewModelProviders.of(this).get(CoinDetailViewModel::class.java)
        viewModel.getCoinHistory(coinId,"24h")
        viewModel.getSingleCoin(coinId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = com.onurkarabulut.coin_ranking_app.databinding.FragmentCoinDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.coinDetailSparkView.lineColor = ContextCompat.getColor(requireContext(), R.color.colorGraph)
        binding.radioButtonDay.isChecked = true
        observeData()
        return view
    }
    private fun observeData(){
        viewModel.coinData.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                binding.coinDetailLayout.visibility = View.VISIBLE
                binding.coinDetailName.setText(it.data.coins!!.name)
                binding.coinDetailSymbol.setText(it.data.coins!!.symbol)
                binding.coinDetailPrice.setText(String.format("%.2f $",it.data.coins!!.price))
                it.data.coins!!.iconUrl?.let {
                    binding.coinDetailImg.loadSvg(it)
                }

            }
        })
        viewModel.coinHistory.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.coinDetailLayout.visibility = View.VISIBLE
                adapter = CoinDetailSparkAdapter(it.data.history)
                binding.coinDetailSparkView.adapter = adapter

                val last = it.data.history.get(it.data.history.size-1)
                buttonChange()
                updateDisplayWithData(last)
            }
        })
        viewModel.coinLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.coinDetailLayout.visibility = View.GONE
                binding.coinDetailProgressBar.visibility = View.VISIBLE
            }else{
                binding.coinDetailProgressBar.visibility = View.GONE
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun updateDisplayWithData(data : CoinHistoryResult.Data.History){
        binding.priceLabel.text = NumberFormat.getInstance().format(data.price) + " $"
        binding.dateLabel.text = getDateTime(data.timestamp)
    }
    private fun getDateTime(s: Long): String? {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s * 1000)
        return sdf.format(netDate).toString()
    }
    private fun buttonChange(){
        binding.priceLabel.setCharacterLists(TickerUtils.provideNumberList())
        radioGroupTimeSelection.setOnCheckedChangeListener { _, id ->
            if (id == R.id.radioButtonDay) viewModel.getCoinHistory(coinId,"24h")
            else if (id == R.id.radioButtonWeek) viewModel.getCoinHistory(coinId,"7d")
            else if (id == R.id.radioButtonMonth) viewModel.getCoinHistory(coinId,"30d")
        }
        binding.coinDetailSparkView.isScrubEnabled = true
        binding.coinDetailSparkView.setScrubListener { itemData ->
            if (itemData is CoinHistoryResult.Data.History){
                updateDisplayWithData(itemData)
            }
        }
    }

}
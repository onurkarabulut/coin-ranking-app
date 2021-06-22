package com.onurkarabulut.coin_ranking_app.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.onurkarabulut.coin_ranking_app.R
import com.onurkarabulut.coin_ranking_app.model.CoinResult
import com.onurkarabulut.coin_ranking_app.util.loadSvg
import com.onurkarabulut.coin_ranking_app.view.CoinListFragmentDirections


import kotlinx.android.synthetic.main.recycler_row.view.*

class CoinListRecyclerAdapter(val coinList : List<CoinResult.Data.Coin>) : RecyclerView.Adapter<CoinListRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.coinName.text = coinList.get(position).name
        holder.itemView.coinSymbol.text = coinList.get(position).symbol
        coinList.get(position).change?.let {
            if (it < 0){
                holder.itemView.coinChange.setBackgroundResource(R.drawable.text_bg2)
                holder.itemView.coinChange.text = String.format("%.2f",it)
            }else{
                holder.itemView.coinChange.setBackgroundResource(R.drawable.text_bg)
                holder.itemView.coinChange.text = String.format("%.2f",it)
            }
        }
        holder.itemView.coinPrice.text = String.format("%.2f $",coinList.get(position).price)
        coinList.get(position).iconUrl?.let {
            holder.itemView.coinImage.loadSvg(it)
        }
        holder.itemView.setOnClickListener { view ->
            coinList.get(position).uuid?.let {
                val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(it)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
    override fun getItemCount(): Int {
        return coinList.size
    }
}
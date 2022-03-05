package com.shenawynkov.currency.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shenawynkov.currency.R
import com.shenawynkov.currency.domain.model.Rate


class RatesAdapter(var list: List<Rate>, val ratesListner: RatesListener): RecyclerView.Adapter<RatesAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val tvCurr: TextView
         val tvRate: TextView

        init {
            // Define click listener for the ViewHolder's View.
            tvCurr = view.findViewById(R.id.tv_curr)
            tvRate = view.findViewById(R.id.tv_rate)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rate, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.tvCurr.text = list[position].key
        viewHolder.tvRate.text = list[position].value.toString()
        viewHolder.itemView.setOnClickListener {
             ratesListner.onRateSelected(list[viewHolder.adapterPosition])
        }
    }

    override fun getItemCount() = list.size


    fun setNewList( newList: List<Rate>)
    {
        this.list=newList;
        notifyDataSetChanged()
    }

    interface  RatesListener{
       fun  onRateSelected(rate: Rate)

    }
}

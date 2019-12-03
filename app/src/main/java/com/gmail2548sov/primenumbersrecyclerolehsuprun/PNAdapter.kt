package com.gmail2548sov.primenumbersrecyclerolehsuprun

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class PNAdapter (private val primeNumbers: ArrayList<Int>): RecyclerView.Adapter<PNAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return  ViewHolder(view)

    }

    override fun getItemCount(): Int {
        Log.d("Us5",primeNumbers.size.toString())
        return primeNumbers.size


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(primeNumbers[position])
    }


    inner class ViewHolder (private val view: View): RecyclerView.ViewHolder(view){
        fun bind(primeNums:Int) {
            Log.d("Us3", primeNums.toString())
            view.textView2.text = primeNums.toString()
        }

    }
}
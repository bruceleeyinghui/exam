package com.example.exam.hmi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.R

class MyAdapter (var resultList: MutableList<Double>) :
    RecyclerView.Adapter<MyAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ListViewHolder, position: Int) {
        val data = resultList[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvResult: TextView

        init {
            tvResult = itemView.findViewById(R.id.tv_result)
        }
        fun bindData(result: Double){
            tvResult.text = result.toString()
        }
    }
}
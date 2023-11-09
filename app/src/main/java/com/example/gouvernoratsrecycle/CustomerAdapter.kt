package com.example.gouvernoratsrecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapter(private var data: List<InfoGovernorates>, private val listener: OnItemClickListener):RecyclerView.Adapter<CustomerAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val name=itemView.findViewById(R.id.name) as TextView
        val photo=itemView.findViewById(R.id.photo) as ImageView
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerAdapter.MyViewHolder {
        val item=LayoutInflater.from(parent.context).inflate(R.layout.lignecard,parent,false);
        return  MyViewHolder(item);
    }

    override fun onBindViewHolder(holder: CustomerAdapter.MyViewHolder, position: Int) {

       var current=data[position];
        holder.name.text=current.name;
        holder.photo.setImageResource(current.imageId);
    }

    override fun getItemCount(): Int {
        return  data.size;
    }

}
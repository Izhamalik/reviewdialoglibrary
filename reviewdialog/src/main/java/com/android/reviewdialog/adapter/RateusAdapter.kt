package com.android.reviewdialog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.exaple.reviewdialog.R

class RateusAdapter : RecyclerView.Adapter<RateusAdapter.MyViewHolder>() {

    private var itemlist = listOf<RateUsModel>()
    lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(
        context: Context, itemlist: List<RateUsModel>
    ) {
        this.context = context
        this.itemlist = itemlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_rateus, parent, false)
        return MyViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (itemlist[position].icon != null)
            holder.profilePic.setImageDrawable(itemlist[position].icon)

        holder.userName.text = itemlist[position].name
        holder.reviewTxt.text = itemlist[position].reviewtext

    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var profilePic: ImageView = itemView.findViewById(R.id.profilePic)
        var userName: TextView = itemView.findViewById(R.id.userName)
        var reviewTxt: TextView = itemView.findViewById(R.id.reviewTxt)
    }

}


    @Keep
    data class RateUsModel(
        var icon: Drawable? = null,
        var name: String = "",
        var reviewtext: String = ""
    )



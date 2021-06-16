package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.remote.model.Products
import java.util.*


class DealItemAdapter(val clickListener: ItemClickHandler) : RecyclerView.Adapter<DealItemAdapter.DealItemViewHolder>() {

    private var dataList: List<Products>? = null
    private var lastPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view, clickListener)
    }

    fun updateData(list: List<Products>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = dataList?.get(position)
        if (item != null) {
            viewHolder.bind(item)
        }
        setAnimation(viewHolder.itemView, position)

    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }


    inner class DealItemViewHolder(itemView: View, clickListener: ItemClickHandler) : RecyclerView.ViewHolder(
        itemView
    ) {
        private val dealListItemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
        private val dealListItemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
        private val dealListImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
        private val dealListAisle = itemView.findViewById<TextView>(R.id.deal_list_size)


        fun bind(item: Products) {
            dealListItemTitle.text = item.title
            dealListItemPrice.text = item.regular_price?.display_string ?: ""
            dealListAisle.text = item.aisle.toUpperCase(Locale.ROOT)
            Picasso.get().load(item.image_url).into(dealListImage)
            itemView.setOnClickListener{
                clickListener.itemClick(item)
            }
        }

    }

    interface ItemClickHandler {
        fun itemClick(item: Products)
    }
}


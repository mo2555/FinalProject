package com.example.finalproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.Model.ProductData
import com.example.finalproject.Model.RestaurantListData
import com.example.finalproject.R

class RestaurantProfileProductsAdapter(var products:List<ProductData>,var restaurantProfile:View):RecyclerView.Adapter<RestaurantProfileProductsAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val productImage : ImageView = itemView.findViewById(R.id.productImage)
        val productName : TextView = itemView.findViewById(R.id.productName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_profile_products_single_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var list = products[position]
        holder.productName.text = list.name
        Glide.with(restaurantProfile).load(list.image).into(holder.productImage)


    }

    override fun getItemCount(): Int {
        return  products.size
    }

}
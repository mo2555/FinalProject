package com.example.finalproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.Model.RestaurantListData
import com.example.finalproject.R
import com.example.finalproject.UI.Fragment.RestaurantsListFragmentDirections

class RestaurantListAdapter(
    var data:List<RestaurantListData>,
    var restaurantsListFragment: View):RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val restaurantName : TextView = itemView.findViewById(R.id.restaurantListName)
        val restaurantImage : ImageView = itemView.findViewById(R.id.restaurantListImage)
        val restaurantMapDir: ImageView = itemView.findViewById(R.id.restaurantListMapDir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_single_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = data[position]
        holder.restaurantName.text = list.name
        Glide.with(restaurantsListFragment).load(list.image).into(holder.restaurantImage)
    holder.restaurantImage.setOnClickListener {
        var action = RestaurantsListFragmentDirections.restaurantListToProfile(list.name,list.image,position)
        Navigation.findNavController(restaurantsListFragment).navigate(action)
    }
        holder.restaurantMapDir.setOnClickListener {
            var action = RestaurantsListFragmentDirections.restaurantListToMap(list.restaurant_lat,list.restaurant_long,list.name)
            Navigation.findNavController(restaurantsListFragment).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return  data.size
    }

}
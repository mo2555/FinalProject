package com.example.finalproject.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.Adapter.RestaurantListAdapter
import com.example.finalproject.Adapter.RestaurantProfileProductsAdapter
import com.example.finalproject.R
import com.example.finalproject.ViewModel.MyViewModel

class RestaurantListProfileFragment : Fragment() {
    lateinit var restaurantProfileProductsAdapter: RestaurantProfileProductsAdapter
    lateinit var recycler: RecyclerView
    lateinit var myViewModel: MyViewModel
    lateinit var restaurantName:String
    lateinit var restaurantImage:String
    lateinit var restaurantProfileImage: ImageView
    lateinit var restaurantProfileName: TextView
    var position:Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        takeArg()

        initial(view)

        makeView(view)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list_profile, container, false)
    }

    fun takeArg(){
        restaurantName = arguments?.getString("restaurantName").toString()
        restaurantImage = arguments?.getString("restaurantImage").toString()
        position = arguments?.getInt("position")!!
    }
    fun initial(view: View){
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        recycler = view.findViewById(R.id.recyclerProducts)
        restaurantProfileImage=view.findViewById(R.id.restaurantListProfileImage)
        restaurantProfileName = view.findViewById(R.id.restaurantListProfileName)
        restaurantProfileName.text = restaurantName
        Glide.with(view).load(restaurantImage).into(restaurantProfileImage)
    }
    fun makeView(view: View){
        myViewModel.getData()
        myViewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {
            var products = it[position].products
            restaurantProfileProductsAdapter = RestaurantProfileProductsAdapter(products,view)
            recycler.layoutManager  = GridLayoutManager(context,2)
            recycler.adapter = restaurantProfileProductsAdapter
        })

        myViewModel.error_message.observe(viewLifecycleOwner , Observer {
            Toast.makeText(view.context , it , Toast.LENGTH_SHORT).show()
        })
    }
}
package com.example.finalproject.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapter.RestaurantListAdapter
import com.example.finalproject.R
import com.example.finalproject.ViewModel.MyViewModel

class RestaurantsListFragment : Fragment() {
    lateinit var restaurantListAdapter: RestaurantListAdapter
    lateinit var recycler: RecyclerView
    lateinit var myViewModel:MyViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initial(view)

        makeView(view)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurants_list, container, false)
    }

    fun initial(view: View){
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        recycler = view.findViewById(R.id.recyclerRestaurantList)
    }

    fun makeView(view: View){
        myViewModel.getData()
        myViewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {
            restaurantListAdapter = RestaurantListAdapter(it,view)
            recycler.layoutManager  = LinearLayoutManager(view.context)
            recycler.adapter = restaurantListAdapter
        })

        myViewModel.error_message.observe(viewLifecycleOwner , Observer {
            Toast.makeText(view.context , it , Toast.LENGTH_SHORT).show()
        })
    }

}
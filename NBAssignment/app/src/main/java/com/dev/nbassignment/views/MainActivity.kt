package com.dev.nbassignment.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.nbassignment.R
import com.dev.nbassignment.local.ItemsDatabase
import com.dev.nbassignment.local.ItemsEntity
import com.dev.nbassignment.repository.ItemsRepository
import com.dev.nbassignment.viewmodel.ItemViewModel
import com.dev.nbassignment.viewmodel.ItemViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This Activity makes an Api call and populates the result in a recycler view.
 **/

class MainActivity : AppCompatActivity(), ItemClickListener, SearchView.OnQueryTextListener {
    private lateinit var itemViewModel: ItemViewModel
    private val itemsList = mutableListOf<ItemsEntity>()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerData()
        searchList()

        val itemsDao by lazy {
            val roomDatabase = ItemsDatabase.getDatabase(this)
            roomDatabase.getItemsDao()
        }

        val repository by lazy {
            ItemsRepository(itemsDao)
        }

        val viewModelFactory = ItemViewModelFactory(repository)
        itemViewModel = ViewModelProviders.of(this, viewModelFactory).get(ItemViewModel::class.java)

        if (isNetworkConnected()) {
            observeTheData()
            CoroutineScope(Dispatchers.IO).launch {
                itemViewModel.insertData()
            }
        } else {
            observeTheData()
        }


    }

    /**
     * This method is use to observe the live data coming from retrofit and setting that data to recycleView
     **/
    private fun observeTheData() {

        itemViewModel.getItems().observe(this, Observer {
            itemsList.clear()
            itemsList.addAll(it)
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            itemsAdapter.updateData(itemsList)

        })
    }

    /**
     * Setting the recycleView component
     */

    private fun setRecyclerData() {
        itemsAdapter = ItemsAdapter(itemsList, this)
        val gridLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = itemsAdapter
        }
    }

    /**
     * The method is use to check the network status of device
     */

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun searchList() {
        searchBox?.isSubmitButtonEnabled = true
        searchBox?.setOnQueryTextListener(this)
    }

    /**
     * passing data to next screen using intent
     */
    override fun onItemClick(itemsEntity: ItemsEntity, position: Int) {
        var intent = Intent(this, ShowDetailActivity::class.java)
        intent.putExtra("image", itemsEntity.image)
        intent.putExtra("title", itemsEntity.title)
        intent.putExtra("subtitle", itemsEntity.subTitle)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchInDatabase(query)
        }
        return true
    }

    /**
     *  searching the query in database
     */

    private fun searchInDatabase(query: String) {
        val searchQuery = "%$query%"
        itemViewModel.searchTitle(searchQuery).observe(this, { list ->
            list.let {
                itemsList.clear()
                itemsList.addAll(it)
                itemsAdapter.updateData(itemsList)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        shimmerLayout.startShimmer()
    }

    override fun onPause() {
        shimmerLayout.stopShimmer()
        super.onPause()
    }

}
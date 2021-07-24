package com.dev.nbassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dev.nbassignment.local.ItemsEntity
import com.dev.nbassignment.repository.ItemsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemsRepository) : ViewModel() {

    fun insertData() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getItemsList()
        }
    }

    fun getItems(): LiveData<List<ItemsEntity>> {
        return repository.getItemList()
    }

    fun searchTitle(searchQuery: String): LiveData<List<ItemsEntity>> {
        return repository.searchTitle(searchQuery).asLiveData()
    }
}
package com.example.a160421105_uts_project_hobby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421105_uts_project_hobby.model.Beritas
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeListViewModel(application: Application):AndroidViewModel(application) {



    var berita = MutableLiveData<ArrayList<Beritas>>()
    var beritaLoadErrorLD = MutableLiveData<Boolean>()
    var loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null



    fun refresh() {
        beritaLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/UTS/queryBerita.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Beritas>>() { }.type
                val result = Gson().fromJson<List<Beritas>>(it, sType)
                berita.value = result as ArrayList<Beritas>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley",it.toString())
                beritaLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }



}
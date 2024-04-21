package com.example.a160421105_uts_project_hobby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421105_uts_project_hobby.model.Beritas
import com.example.a160421105_uts_project_hobby.model.detilBeritas
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects


class DetailListViewModel(application: Application):AndroidViewModel(application) {

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val detilLD = MutableLiveData<ArrayList<detilBeritas>>()
    fun fetch(idBerita: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/UTS/queryParagraf.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,url,
            {
                val sType = object : TypeToken<List<detilBeritas>>() { }.type
                val result = Gson().fromJson<List<detilBeritas>>(it, sType)
                detilLD.value = result as ArrayList<detilBeritas>?
                Log.d("showvoley-queryParagraf", result.toString())

            },
            {
                Log.d("showvoley-queryParagraf",it.toString())
            }
        )
        {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["idBerita"] = idBerita
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }



}
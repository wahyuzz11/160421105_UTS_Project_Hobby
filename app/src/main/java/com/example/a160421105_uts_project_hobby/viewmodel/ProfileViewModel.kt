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
import com.example.a160421105_uts_project_hobby.model.Users
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileViewModel(application: Application):AndroidViewModel(application) {

    val TAG = "volleyTag"
    val userLD = MutableLiveData<Users>()
    private var queue: RequestQueue? = null


    fun update(idUser: String,namaDepan: String, namaBelakang: String, password: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/UTS/updateUser.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                val sType = object : TypeToken<Users>() { }.type
                val result = Gson().fromJson<Users>(it, sType)
                userLD.value = result
                Log.d("showvoley", it)

            },
            {
                Log.d("showvoley",it.toString())
            })
        {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id"] = idUser
                params["nama_depan"] = namaDepan
                params["nama_belakang"] = namaBelakang
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }






}
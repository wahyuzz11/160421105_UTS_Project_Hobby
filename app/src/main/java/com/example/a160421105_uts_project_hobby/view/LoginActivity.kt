package com.example.a160421105_uts_project_hobby.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421105_uts_project_hobby.R
import com.example.a160421105_uts_project_hobby.databinding.ActivityLoginBinding
import com.example.a160421105_uts_project_hobby.model.Global
import com.example.a160421105_uts_project_hobby.model.Users
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnLogin.setOnClickListener{
                var username = txtInputUsername.text.toString()
                var password = txtInputPassword.text.toString()
                if(password != ""){
                    val q = Volley.newRequestQueue(this@LoginActivity)
                    val url = "http://10.0.2.2/ANMP/UTS/login.php"
                    val stringRequest = object : StringRequest(
                        Request.Method.POST,
                        url,
                        Response.Listener{
                            Log.d("apiresult", it)
                            val obj = JSONObject(it)
                            if (obj.getString("result") == "OK"){
                                val data = obj.getJSONArray("data")
                                val sType = object : TypeToken<List<Users>>() {}.type
                                Global.users = Gson().fromJson(data.toString(), sType) as ArrayList<Users>
                                Global.currentUser = Global.users[0]
                                Log.d("apiresult", Global.currentUser.toString())
                                Toast.makeText(this@LoginActivity, "Login Berhasil !", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                this@LoginActivity.startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(this@LoginActivity, "Username or Password are wrong!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener{
                            Log.e("apiresult", it.message.toString())
                            Toast.makeText(this@LoginActivity, "Username or Password are wrong!", Toast.LENGTH_SHORT).show()
                        }
                    )
                    {
                        override fun getParams(): MutableMap<String, String>? {
                            val params = HashMap<String, String>()
//                        params["id"] = intent.getStringExtra(PlaylistFragment.LATESTID).toString()
                            params["username"] = username.toString()
                            params["password"] = password.toString()
                            return params
                        }
                    }
                    q.add(stringRequest)
                }
            }

            btnRegister.setOnClickListener{
                val intent = Intent(it.context, RegisterActivity::class.java)
                it.context.startActivity(intent)
            }

        }
    }
}
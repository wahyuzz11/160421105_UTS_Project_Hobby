package com.example.a160421105_uts_project_hobby.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160421105_uts_project_hobby.R
import com.example.a160421105_uts_project_hobby.databinding.ActivityRegisterBinding
import com.example.a160421105_uts_project_hobby.model.Users
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnRegisterAcc.setOnClickListener {
                    var username = txtInputUsernameReg.text.toString()
                    var pass = txtInputPass.text.toString()
                    var nama_depan = txtInputNamaDep.text.toString()
                    var nama_belakang = txtInputNamaBel.text.toString()
                    var email = txtInputEmail.text.toString()

                    if(pass.isBlank() || username.isBlank() || nama_depan.isBlank() || nama_belakang.isBlank() || email.isBlank()){
                        Toast.makeText(it.context, "Data tidak boleh kosong !", Toast.LENGTH_SHORT).show()
                    }else{
                        if(txtInputPass.text.toString() == txtInputPasswordRetype.text.toString()) {
                            val q = Volley.newRequestQueue(this@RegisterActivity)
                            val url = "http://10.0.2.2/ANMP/UTS/register.php";
                            val stringRequest = object : StringRequest(
                                Request.Method.POST,
                                url,
                                Response.Listener {
                                    Log.d("cekparams", it)
                                    val obj = JSONObject(it)
                                    if (obj.getString("result") == "OK"){
                                        Toast.makeText(this@RegisterActivity, "Account created!", Toast.LENGTH_SHORT).show()
                                        finish()
                                    }
                                },
                                Response.ErrorListener {
                                    Log.d("cekparams", it.message.toString())
                                }
                            )
                            {
                                override fun getParams(): MutableMap<String, String>? {
                                    val params = HashMap<String, String>()
                                    params["username"] = txtInputUsernameReg.text.toString()
                                    params["password"] = txtInputPass.text.toString()
                                    params["nama_depan"] = txtInputNamaDep.text.toString()
                                    params["nama_belakang"] = txtInputNamaBel.text.toString()
                                    params["email"] = txtInputEmail.text.toString()
                                    return params
                                }
                            }
                            q.add(stringRequest)
                            finish()


                        }else{
                            Toast.makeText(it.context, "Password tidak sama !", Toast.LENGTH_SHORT).show()
                        }
                    }



            }
        }
    }
}
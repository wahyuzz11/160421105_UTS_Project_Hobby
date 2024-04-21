package com.example.a160421105_uts_project_hobby.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("id")
    val id:String = "",
    @SerializedName("username")
    val username:String = "",
    @SerializedName("nama_depan")
    val namaDepan:String = "",
    @SerializedName("nama_belakang")
    val namaBelakang:String = "",
    @SerializedName("password")
    val password:String = "",
    @SerializedName("email")
    val email:String = ""
)

data class Beritas(
    @SerializedName("id")
    val id:String?,
    @SerializedName("judul")
    val judul:String?,
    @SerializedName("gambar")
    val imgUrl:String?,
    @SerializedName("deskripsi")
    val deskripsi:String?,
    @SerializedName("penulis")
    val penulis:String?
)


data class detilBeritas(
    @SerializedName("id")
    val id:String?,
    @SerializedName("judul")
    val judulParagraf:String?,
    @SerializedName("paragraf")
    val paragraf:String?,
    @SerializedName("berita_id")
    val idBerita:String?
)



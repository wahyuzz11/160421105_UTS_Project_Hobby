package com.example.a160421105_uts_project_hobby.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.Navigation
import com.example.a160421105_uts_project_hobby.databinding.HobbyItemListBinding
import com.example.a160421105_uts_project_hobby.model.Beritas
import com.squareup.picasso.Picasso


class HomeListAdapter(val beritaList:ArrayList<Beritas>):RecyclerView.Adapter<HomeListAdapter.BeritaViewHolder>(){


    class BeritaViewHolder(var binding:HobbyItemListBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = HobbyItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BeritaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, uri, exception -> exception.printStackTrace()}
        Picasso.get().load(beritaList[position].imgUrl).into(holder.binding.imgHobby)
        holder.binding.txtTitle.text = beritaList[position].judul
        holder.binding.txtDescription.text = beritaList[position].deskripsi
        holder.binding.txtPenulis.text = beritaList[position].penulis
        holder.binding.btnRead.setOnClickListener{
            val action = HomeFragmentDirections.actionDetailFragment(beritaList[position].id.toString(),beritaList[position].judul.toString(),beritaList[position].imgUrl.toString(),
                beritaList[position].penulis.toString())
            Navigation.findNavController(it).navigate(action)

        }
    }

    fun updateBeritaList(newBeritaList:ArrayList<Beritas>){
        beritaList.clear()
        beritaList.addAll(newBeritaList)
        notifyDataSetChanged()
    }


}
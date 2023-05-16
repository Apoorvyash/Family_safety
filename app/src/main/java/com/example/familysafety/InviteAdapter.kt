package com.example.familysafety

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InviteAdapter (private val listContacts: List<InviteModel>): RecyclerView.Adapter<InviteAdapter.ViewHolder>() {


    class ViewHolder(private val item: View ) : RecyclerView.ViewHolder(item) {
            val name = item.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.invite_mem, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=listContacts[position]
        holder.name.text=item.name
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }
}
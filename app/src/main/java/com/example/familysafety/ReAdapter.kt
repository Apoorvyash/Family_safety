package com.example.familysafety

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ReAdapter(private val listMembers: List<MemberModel>) : RecyclerView.Adapter<ReAdapter.ViewHolder>() {
    class ViewHolder(private val item: View): RecyclerView.ViewHolder(item) {
        val profileImage: ImageView = item.findViewById<ImageView>(R.id.user_image)
        val name: TextView = item.findViewById<TextView>(R.id.name)
        val address: TextView=item.findViewById(R.id.address)
        val distance: TextView=item.findViewById<TextView>(R.id.dist_value)
        val battery: TextView=item.findViewById<TextView>(R.id.battery_percent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater=LayoutInflater.from(parent.context)
        val item= inflater.inflate(R.layout.item_mem, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item=listMembers[position]
            holder.name.text=item.name
            holder.address.text=item.address
            holder.distance.text=item.distance
        }
    override fun getItemCount(): Int {
        return listMembers.size
    }
}
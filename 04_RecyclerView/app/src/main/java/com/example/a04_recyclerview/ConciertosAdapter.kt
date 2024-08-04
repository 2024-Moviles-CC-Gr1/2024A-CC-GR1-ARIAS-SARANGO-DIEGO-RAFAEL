package com.example.a04_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ConciertoItem(val imageRes: Int, val title: String, val date: String, val location: String, val guide: String)

class ConciertosAdapter(private val items: List<ConciertoItem>) : RecyclerView.Adapter<ConciertosAdapter.ConciertoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConciertoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_concierto, parent, false)
        return ConciertoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConciertoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ConciertoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.concierto_image)
        private val title: TextView = view.findViewById(R.id.concierto_title)
        private val date: TextView = view.findViewById(R.id.concierto_date)
        private val location: TextView = view.findViewById(R.id.concierto_location)
        private val guide: TextView = view.findViewById(R.id.concierto_guide)

        fun bind(item: ConciertoItem) {
            image.setImageResource(item.imageRes)
            title.text = item.title
            date.text = item.date
            location.text = item.location
            guide.text = item.guide
        }
    }
}


package com.example.mybook.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybook.R
import com.example.mybook.data.model.Theme
import kotlinx.android.synthetic.main.item_book.view.*

class ThemeAdapter : RecyclerView.Adapter<ThemeAdapter.BookListViewHolder>() {

    var models: List<Theme> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemCLicked: (id: Int) -> Unit = {}

    inner class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(theme: Theme) {
            itemView.tvName.text = theme.name
            itemView.setOnClickListener {
                onItemCLicked.invoke(theme.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return BookListViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}
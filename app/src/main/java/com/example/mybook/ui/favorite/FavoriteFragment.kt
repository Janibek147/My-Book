package com.example.mybook.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mybook.R
import com.example.mybook.data.dao.MyDao
import com.example.mybook.data.model.Theme
import com.example.mybook.data.model.ThemeDatabase
import com.example.mybook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    private val myAdapter = FavoriteAdapter()
    lateinit var dao : MyDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewFavorite.adapter = myAdapter
        recyclerviewFavorite.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        dao = ThemeDatabase.getInstance(requireContext()).dao()
        myAdapter.onItemCLicked = {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.TEXT_ID,it)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        setTheme()
    }

    private fun setTheme() {
        myAdapter.models = dao.getFavoriteTheme()
    }
//     fun setData(models: List<Theme>) {
//        myAdapter.models = models
//        if (myAdapter.models.isEmpty()) linearLayout.visibility = View.VISIBLE
//        else linearLayout.visibility = View.INVISIBLE
//    }
}
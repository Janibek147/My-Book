package com.example.mybook.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mybook.MainActivity
import com.example.mybook.R
import com.example.mybook.data.dao.MyDao
import com.example.mybook.data.model.ThemeDatabase
import com.example.mybook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment(R.layout.fragment_theme) {

    private val myAdapter = ThemeAdapter()
    private lateinit var dao: MyDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.adapter = myAdapter
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val id = arguments?.getInt(MainActivity.chapter_id) ?: 1
        dao = ThemeDatabase.getInstance(requireContext()).dao()
        myAdapter.onItemCLicked = {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.TEXT_ID, it)
            startActivity(intent)
        }
        setData(id)
    }

    private fun setData(id: Int) {
        myAdapter.models = dao.getAllTheme()
        myAdapter.models = dao.getTextByChapterId(id)
    }
}

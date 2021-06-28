package com.example.mybook.ui.detail

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import com.example.mybook.R
import com.example.mybook.data.dao.MyDao
import com.example.mybook.data.model.Theme
import com.example.mybook.data.model.ThemeDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.app_bar_main.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val TEXT_ID = "themeId"
    }
    private lateinit var pref:SharedPreferences
    private var themeId: Int = 0
    private lateinit var theme: Theme
    private val compositeDisposable = CompositeDisposable()
    private lateinit var dao: MyDao
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        pref=getSharedPreferences("MySharedPreferences",Activity.MODE_PRIVATE)
        tvText.textSize=pref.getFloat("textSize",20f)
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        themeId = intent.getIntExtra(TEXT_ID, 1)
        dao = ThemeDatabase.getInstance(this).dao()
        progress.isVisible = true
        compositeDisposable.add(
            dao.getTextById(themeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        theme = it
                        supportActionBar?.title = theme.name
                        tvText.text = HtmlCompat.fromHtml(theme.text.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
                        progress.isVisible = false
                    },
                    {
                        progress.isVisible = false
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.item_bookmark -> setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        theme.favorite = 1 - theme.favorite
        setFavoriteIcon()
        dao.updateTheme(theme)
    }

    private fun setFavoriteIcon() {
        if (theme.favorite == 1) {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_24)
        } else {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }
}

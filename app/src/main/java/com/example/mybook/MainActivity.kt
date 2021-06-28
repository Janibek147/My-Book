package com.example.mybook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.mybook.ui.aboutus.AboutUsFragment
import com.example.mybook.ui.detail.DetailActivity
import com.example.mybook.ui.favorite.FavoriteFragment
import com.example.mybook.ui.settings.SettingsFragment
import com.example.mybook.ui.theme.ThemeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val chapter_id = "chapter_id"
        const val chapter_1 = 1
        const val chapter_2 = 2
        const val chapter_3 = 3
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toogle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close

        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        navView.setNavigationItemSelectedListener {
            val mfragment = ThemeFragment()
            val mbundle = Bundle()
            mfragment.arguments = mbundle
            when (it.itemId) {
                R.id.nav_chapter1 -> {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TEXT_ID, 23)
                    startActivity(intent)
                }
                R.id.nav_chapter2 -> {
                    mbundle.putInt(chapter_id, 2)
                    mfragment.arguments = mbundle
                }
                R.id.nav_chapter3 -> {
                    mbundle.putInt(chapter_id, 3)
                    mfragment.arguments = mbundle
                }
                R.id.nav_favorite -> {
                    val kfragment = FavoriteFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentConteiner, kfragment).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_settings -> {
                    val kfragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentConteiner, kfragment).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_share -> {
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_aboutUs -> {
                    val kfragment = AboutUsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentConteiner, kfragment).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.FragmentConteiner, mfragment)
                .commit()
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
        val fragment = ThemeFragment()
        val bundle = Bundle()
        bundle.putInt(chapter_id, 1)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.FragmentConteiner, fragment)
            .addToBackStack(ThemeFragment::class.simpleName).commit()
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }
}
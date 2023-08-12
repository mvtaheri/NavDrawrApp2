package com.mohammad.navdrawrapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer)
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener() {
            it.isChecked = true
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), it.title.toString())
                }

                R.id.login -> {
                    replaceFragment(LoginFragment(), it.title.toString())
                }

                R.id.setting -> {
                    replaceFragment(SettingFragment(), it.title.toString())
                }

                R.id.message -> {
                    replaceFragment(MessageFragment(), it.title.toString())
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        var fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_1, fragment)
        fragmentTransaction.commit()
        setTitle(title)
        drawerLayout.closeDrawers()
    }
}
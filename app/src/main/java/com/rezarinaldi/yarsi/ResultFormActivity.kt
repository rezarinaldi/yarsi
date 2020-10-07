package com.rezarinaldi.yarsi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.rezarinaldi.yarsi.databinding.ActivityResultFormBinding
import com.rezarinaldi.yarsi.models.Parent
import com.rezarinaldi.yarsi.models.Personal
import com.rezarinaldi.yarsi.models.School

class ResultFormActivity : AppCompatActivity() {
    private val DATA_PRIBADI = "DATA_PRIBADI"
    private val DATA_PARENT = "DATA_PARENT"
    private val DATA_SCHOOL = "DATA_SCHOOL"
    private lateinit var binding: ActivityResultFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result_form)

        binding.school = intent.getParcelableExtra<School>(DATA_SCHOOL)!!
        binding.parent = intent.getParcelableExtra<Parent>(DATA_PARENT)!!
        binding.pribadi = intent.getParcelableExtra<Personal>(DATA_PRIBADI)!!

        Log.e(DATA_SCHOOL, binding.school.toString())
        Log.e(DATA_PARENT, binding.parent.toString())
        Log.e(DATA_PRIBADI, binding.pribadi.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1 , Menu.NONE, "Light Mode")
        menu?.add(Menu.NONE, 2 , Menu.NONE, "Dark Mode")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        return super.onOptionsItemSelected(item)
    }
}
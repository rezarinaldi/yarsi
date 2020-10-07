package com.rezarinaldi.yarsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.rezarinaldi.yarsi.databinding.ActivityParentDataBinding
import com.rezarinaldi.yarsi.models.Parent
import com.rezarinaldi.yarsi.models.Personal

class ParentDataActivity : AppCompatActivity() {
    private val DATA_PRIBADI = "DATA_PRIBADI"
    private val DATA_PARENT = "DATA_PARENT"
    private lateinit var binding: ActivityParentDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_data)

        binding.btnParentToSchool.setOnClickListener {
            intentToSchool()
        }
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

    private fun intentToSchool() {
        val dataParent = Parent(
            namaAyah = binding.editTextNamaAyah.text.toString(),
            nikAyah = binding.editTextNikAyah.text.toString(),
            namaIbu = binding.editTextNamaIbu.text.toString(),
            nikIbu = binding.editTextNikIbu.text.toString(),
            tanggalLahirAyah = binding.editTextLahirAyah.text.toString(),
            tanggalLahirIbu = binding.editTextLahirIbu.text.toString(),
            alamatParent = binding.editTextAlamat.text.toString(),
            phoneOrtu = binding.editTextPhone.text.toString(),
            emailParent = binding.editTextEmail.text.toString(),
            pendidikanAyah = binding.editTextPendidikanAyah.text.toString(),
            pendidikanIbu = binding.editTextPendidikanIbu.text.toString(),
            pekerjaanAyah = binding.editTextPekerjaanAyah.text.toString(),
            pekerjaanIbu = binding.editTextPekerjaanIbu.text.toString(),
        )
        val dataPribadi = intent.getParcelableExtra<Personal>(DATA_PRIBADI)!!
        val resultIntent = Intent(this, SchoolDataActivity::class.java)
        resultIntent.putExtra(DATA_PARENT, dataParent)
        resultIntent.putExtra(DATA_PRIBADI, dataPribadi)
        startActivity(resultIntent)
    }
}
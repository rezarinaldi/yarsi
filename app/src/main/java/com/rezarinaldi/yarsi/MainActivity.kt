package com.rezarinaldi.yarsi

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.rezarinaldi.yarsi.databinding.ActivityMainBinding
import com.rezarinaldi.yarsi.models.Personal
import java.util.*

class MainActivity : AppCompatActivity() {
    private val DATA_PRIBADI = "DATA_PRIBADI"
    private lateinit var binding: ActivityMainBinding

    private lateinit var dataFakultas: Array<String>
    private lateinit var dataProdi: Array<String>
    private lateinit var dataStatus: Array<String>
    private lateinit var dataAgama: Array<String>
    private lateinit var dataProvinsi: Array<String>
    private lateinit var dataKota: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUp()
        loadDataSpinner()

        binding.editTextDate.setOnClickListener {
            datePicker()
        }

        binding.btnPersonaltoParent.setOnClickListener {
            intentToParent()
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

    private fun setUp() {
        dataFakultas = resources.getStringArray(R.array.dataFakultas)
        dataProdi = resources.getStringArray(R.array.dataProdi)
        dataStatus = resources.getStringArray(R.array.dataStatus)
        dataAgama = resources.getStringArray(R.array.dataAgama)
        dataProvinsi = resources.getStringArray(R.array.dataProvinsi)
        dataKota = resources.getStringArray(R.array.dataKota)
    }

    private fun loadDataSpinner() {
        val adapterFakultas = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataFakultas)
        val adapterProdi = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataProdi)
        val adapterStatus = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataStatus)
        val adapterAgama = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataAgama)
        val adapterProvinsi = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataProvinsi)
        val adapterKota = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataKota)

        adapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterProdi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerFakultas.adapter = adapterFakultas
        binding.spinnerProdi.adapter = adapterProdi
        binding.spinnerStatus.adapter = adapterStatus
        binding.spinnerAgama.adapter = adapterAgama
        binding.spinnerProvinsi.adapter = adapterProvinsi
        binding.spinnerKota.adapter = adapterKota
    }

    private fun datePicker() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val bulan = (monthOfYear + 1)
                binding.editTextDate.setText("$dayOfMonth/$bulan/$year")
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    private fun radioButtonSelectionSex(): String {
        val selected: Int = binding.radioGroupSex.checkedRadioButtonId
        return if (selected == binding.radioButtonPerempuan.id) {
            binding.radioButtonPerempuan.text.toString()
        } else {
            binding.radioButtonLaki.text.toString()
        }
    }

    private fun radioButtonSelectionNation(): String {
        val selected: Int = binding.radioGroupNationality.checkedRadioButtonId
        return if (selected == binding.radioButtonAsing.id) {
            binding.radioButtonAsing.text.toString()
        } else {
            binding.radioButtonIndonesia.text.toString()
        }
    }

    private fun intentToParent() {
        val dataPribadi = Personal(
            nama = binding.editTextNama.text.toString(),
            fakultas = binding.spinnerFakultas.selectedItem.toString(),
            prodi = binding.spinnerProdi.selectedItem.toString(),
            status = binding.spinnerStatus.selectedItem.toString(),
            password = binding.editTextPassword.text.toString(),
            alasan = binding.editTextAlasan.text.toString(),
            nik = binding.editTextNik.text.toString(),
            prestasi = binding.editTextPrestasi.text.toString(),
            tempat = binding.editTextPlace.text.toString(),
            tanggal = binding.editTextDate.text.toString(),
            jenisKelamin = radioButtonSelectionSex(),
            kewarganegaraan = radioButtonSelectionNation(),
            agama = binding.spinnerAgama.selectedItem.toString(),
            alamat = binding.editTextAlamat.text.toString(),
            rt = binding.editTextRT.text.toString(),
            rw = binding.editTextRW.text.toString(),
            kodePos = binding.editTextPostal.text.toString(),
            provinsi = binding.spinnerProvinsi.selectedItem.toString(),
            kota = binding.spinnerKota.selectedItem.toString(),
            phone = binding.editTextPhone.text.toString(),
            email = binding.editTextEmail.text.toString()
        )
        val resultIntent = Intent(this, ParentDataActivity::class.java)
        resultIntent.putExtra(DATA_PRIBADI, dataPribadi)
        startActivity(resultIntent)
    }
}
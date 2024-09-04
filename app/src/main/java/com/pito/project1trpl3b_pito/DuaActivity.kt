package com.pito.project1trpl3b_pito

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DuaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val pilKeberangkatan = arrayOf<String>("06:00 pm", "06:00 am", "12:00 pm", "04:00 pm", "08:23 am", "03:56 pm", "12:00 am", "01:34 pm", "08:12 pm", "07:23 pm")
    lateinit var rbBayar: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dua)

        val makanan = findViewById<CheckBox>(R.id.chkMakanan)
        val lounge = findViewById<CheckBox>(R.id.chkExcekute)
        val bagasi = findViewById<CheckBox>(R.id.chkBagasi)
        val bayar = findViewById<RadioGroup>(R.id.rgBayar)
        val tujuan = findViewById<EditText>(R.id.editTujuan)
        val harga = findViewById<EditText>(R.id.editHarga)
        val jumlah = findViewById<EditText>(R.id.editJumlah)
        val btnProses = findViewById<Button>(R.id.btnProses)
        val spinBerangkat = findViewById<Spinner>(R.id.spinkeberangkatan)
        val output = findViewById<TextView>(R.id.txtTrans)

        spinBerangkat.onItemSelectedListener = this

        val aa: ArrayAdapter<String> = ArrayAdapter(
            this@DuaActivity,
            R.layout.spinner_style,
            pilKeberangkatan
        )
        aa.setDropDownViewResource(R.layout.spinner_style)
        spinBerangkat.adapter = aa

        btnProses.setOnClickListener {
            val tujuanP = tujuan.text.toString()
            val hargaP = harga.text.toString().toDouble()
            val jumlahP = jumlah.text.toString().toInt()
            val totTik = hargaP * jumlahP
            val berangkat = spinBerangkat.selectedItem.toString()
            var totalbayar: Int=0 // deklarasi variable dengan tipe data
            val tambahan = StringBuffer(); // cara membuat object di kotlin

            if (lounge.isChecked){
                tambahan.append("\nExecutive Lounge Rp. 125.000,00")
                totalbayar += 125000
            }
            if (makanan.isChecked){
                tambahan.append("\nMakanan Minumanan Rp. 300.000,00")
                totalbayar += 300000
            }
            if (bagasi.isChecked){
                tambahan.append("\nBagasi Rp. 115.000,00")
                totalbayar += 115000
            }

            tambahan.append("\nTotal Biaya Tambahan : " + totalbayar)
            val selectedRadio: Int = bayar.checkedRadioButtonId
            rbBayar = findViewById(selectedRadio)
            val jBayar = totTik + totalbayar

            output.text = "Detail Pembayaran = " +
                    "\nTujuan : $tujuanP" +
                    "\nHarga Tiket : $hargaP" +
                    "\nJumlah Tiket : $jumlahP" +
                    "\nJam Berangkat : $berangkat" +
                    "\ntotal bayar tiket : $totTik" +
                    "\nBiaya Tambahan : ${tambahan.toString()}" +
                    "\nJumlah Bayar : $jBayar"+
                    "\nCara Bayar : ${rbBayar.text.toString()}"
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val selectedItem:String = parent?.getItemAtPosition(position).toString()
//        Toast.makeText(this@DuaActivity, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}

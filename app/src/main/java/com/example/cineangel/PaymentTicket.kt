package com.example.cineangel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.cineangel.databinding.ActivityPaymentTicketBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentTicket : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentTicketBinding

    //DAFTAR HARGA SEAT
    private val seatPrice = arrayOf(0, 35000, 40000, 50000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MENYIAPKAN TAMPILAN SPINNER DAN MENYIAPKAN KLIK TOMBOL
        setupSpinners()
        setupButtonListeners()
    }

    //UNTUK MENYIAPKAN SPINNER (DROPDOWN MENUS)
    private fun setupSpinners() {
        val bioskop = resources.getStringArray(R.array.bioskop)
        val seat = resources.getStringArray(R.array.seats)
        val payment = resources.getStringArray(R.array.payment)

        val bioskopAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bioskop)
        val seatAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, seat)
        val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, payment)

        //MENGAITKAN ADAPTER DENGAN SPINNER
        binding.spinnerBioskop.adapter = bioskopAdapter
        binding.spinnerSeat.adapter = seatAdapter
        binding.spinnerPayment.adapter = paymentAdapter

        //MENANGANI PERUBAHAN PEMILIHAN KURSI
        binding.spinnerSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedSeatPrice = seatPrice[position]
                binding.tvPriceSet.text = "Rp$selectedSeatPrice"
                binding.tvTotPrice.text = binding.tvPriceSet.text
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.tvPriceSet.text = "Rp0-"
                binding.tvTotPrice.text = binding.tvPriceSet.text
            }
        }

        //MENYIAPKAN ADAPTER DAN MENGAITKANNYA DENGAN SPINNER UNTUK METODE PEMBAYARAN BANK DAN QR
        val paymentBankArray = resources.getStringArray(R.array.paymentbank)
        val paymentQRArray = resources.getStringArray(R.array.paymentqr)

        val paymentBankAdapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, paymentBankArray)
        val paymentQRAdapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, paymentQRArray)

        //MENANGANI PERUBAHAN PEMILIHAN METODE PEMBAYARAN
        binding.spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> binding.spinnerPaymentP.adapter = paymentBankAdapter
                    2 -> binding.spinnerPaymentP.adapter = paymentQRAdapter
                }
            }


            //bagian dari implementasi AdapterView.OnItemSelectedListener, yang digunakan untuk mendengarkan perubahan pemilihan item pada spinner (dropdown menu) di aplikasi.
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    //MENYIAPKAN LISTENER TOMBOL
    private fun setupButtonListeners() {
        binding.back.setOnClickListener {
            val intentDetails = Intent(this, Details::class.java)
            startActivity(intentDetails)
        }

        //MENGHANDLE PEMILIHAN TANGGAL
        binding.btnTanggal.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(supportFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
                binding.tvTanggalSet.text = simpleDateFormat.format(Date(it).time)
            }
        }

        //MENGHANDLE TOMBOL ORDER SUMMARY
        binding.ordrsummerybtn.setOnClickListener {
            val selectedDate = binding.tvTanggalSet.text.toString()
            val selectedAccNumber = binding.accountNumber.text.toString()
            val selectedPayment = binding.spinnerPayment.selectedItem.toString()
            val selectedPaymentPick = binding.spinnerPaymentP.selectedItem.toString()
            val selectedBioskop = binding.spinnerBioskop.selectedItem.toString()
            val selectedSeat = binding.spinnerSeat.selectedItem.toString()

            if (selectedDate.isNotBlank() && selectedDate != "Select" &&
                selectedAccNumber.isNotBlank() &&
                selectedPayment != "Select" &&
                selectedPaymentPick != "Select" &&
                selectedBioskop != "Select" &&
                selectedSeat != "Select"
            ) {
                val currentDate = SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(Date())
                val random = (1..100).random()
                val orderNumber = "$currentDate-$random"

                val intent = Intent(this, OrderSummary::class.java)
                intent.putExtra("selectedDate", selectedDate)
                intent.putExtra("accNumber", selectedAccNumber)
                intent.putExtra("payment", selectedPayment)
                intent.putExtra("paymentPick", selectedPaymentPick)
                intent.putExtra("bioskop", selectedBioskop)
                intent.putExtra("seat", selectedSeat)
                intent.putExtra("price", binding.tvTotPrice.text)
                intent.putExtra("orderNumber", orderNumber)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Masukkan semua informasi yang diperlukan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

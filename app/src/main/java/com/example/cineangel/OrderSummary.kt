package com.example.cineangel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cineangel.databinding.ActivityOrderSummaryBinding
import java.text.SimpleDateFormat
import java.util.Locale

class OrderSummary : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MENDAPATKAN DATA DARI INTENT
        val bioskop = intent.getStringExtra("bioskop")
        val selectedDate = intent.getStringExtra("selectedDate")
        val orderNumber = intent.getStringExtra("orderNumber")
        val payment = intent.getStringExtra("payment")
        val paymentPick = intent.getStringExtra("paymentPick")
        val price = intent.getStringExtra("price")
        val seat = intent.getStringExtra("seat")

        //MEMFORMAT TANGGAL
        val formattedDate = formatDateString(selectedDate)

        with(binding) {
            back.setOnClickListener {
                finish()
            }

            //MENETAPKAN TEKS KE ELEMEN TAMPILAN
            tvTitle.text = "Spider-Man"
            tvBioskop.text = bioskop
            tvDate.text = formattedDate
            tvOrderNumber.text = orderNumber
            tvPayment.text = "$payment ($paymentPick)"
            tvPrice.text = price
            tvPay.text = tvPrice.text
            tvSeat.text = "$seat Seat"
        }
    }

    private fun formatDateString(date: String?): String {
        //MENGONVERSI FORMAT TANGGAL
        val inputFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
        return outputFormat.format(inputFormat.parse(date))
    }
}

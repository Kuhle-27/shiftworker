package com.app.shiftworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHours = findViewById<EditText>(R.id.etHours).text
        val etShift = findViewById<EditText>(R.id.etShift).text
        val btnCalculate = findViewById<Button>(R.id.btnCalc)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {

            var overtimeHours = 0.0
            var hours = etHours.toString().toDouble()
            var currentRate = 0.0
            var overtimePay = 0.0
            var overtimeRate = 0.0
            var retirement = 0.0

            if (hours > 40) {
                overtimeHours = hours - 40
                hours = 40.0
            }

            if (etShift.toString().toInt() == 1) {
                currentRate = 17.0
            } else if (etShift.toString().toInt() == 2) {
                currentRate = 18.50
            } else if (etShift.toString().toInt() == 3) {
                currentRate = 22.0
            } else {
                tvResult.text = "You have entered an invalid shift"
            }

            if (overtimeHours > 0) {
                overtimeRate = currentRate * 1.5
                overtimePay = overtimeHours * overtimeRate
            }

            val regularPay = hours * currentRate
            val totalPay = regularPay + overtimePay

            if (etShift.toString().toInt() == 2 || etShift.toString().toInt() == 3) {
                retirement = totalPay * 0.03
            }

            val netPay = totalPay - retirement

            tvResult.text = "Hours worked = R${hours + overtimeHours} \n" +
                    "Shift = ${etShift} \n" +
                    "Hourly pay rate = R${currentRate} \n" +
                    "Regular pay = R${regularPay} \n" +
                    "Overtime pay = R${overtimePay} \n" +
                    "Total pay = R${totalPay} \n" +
                    "Retirement = R${retirement} \n" +
                    "Net pay = R${netPay}"

        }
    }
}
package com.brunocorrea.conta

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var account: BankAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        account = BankAccount(owner = "Bruno Correa", balance = 0.0)

        val tvOwner = findViewById<TextView>(R.id.tvOwner)
        val tvBalance = findViewById<TextView>(R.id.tvBalance)
        val etValue = findViewById<EditText>(R.id.etValue)
        val btnDeposit = findViewById<Button>(R.id.btnDeposit)
        val btnWithdraw = findViewById<Button>(R.id.btnWithdraw)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)

        tvOwner.text = "Titular: ${account.getOwner()}"
        updateBalance(tvBalance)

        btnDeposit.setOnClickListener {
            val value = etValue.text.toString().replace(",", ".").toDoubleOrNull()
            if (value == null) {
                tvMessage.text = "Informe um valor válido."
                return@setOnClickListener
            }
            try {
                account.deposit(value)
                tvMessage.text = "Depósito realizado."
                updateBalance(tvBalance)
                etValue.text.clear()
            } catch (e: IllegalArgumentException) {
                tvMessage.text = e.message
                vibrateOnError()
            }
        }

        btnWithdraw.setOnClickListener {
            val value = etValue.text.toString().replace(",", ".").toDoubleOrNull()
            if (value == null) {
                tvMessage.text = "Informe um valor válido."
                return@setOnClickListener
            }
            try {
                account.withdraw(value)
                tvMessage.text = "Saque realizado."
                updateBalance(tvBalance)
                etValue.text.clear()
            } catch (e: IllegalArgumentException) {
                tvMessage.text = e.message
                vibrateOnError() // recurso do dispositivo
            }
        }
    }

    private fun updateBalance(tv: TextView) {
        val fmt = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        tv.text = "Saldo: ${fmt.format(account.balance)}"
    }

    private fun vibrateOnError() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val effect = VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
        v.vibrate(effect)
    }
}

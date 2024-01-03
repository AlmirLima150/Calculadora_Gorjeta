package com.almirlima.calculadoragorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.almirlima.calculadoragorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var valor: EditText
    private lateinit var seekBarEscala: SeekBar
    private lateinit var txtPorcentagem: TextView
    private lateinit var txtGorjeta : TextView
    private lateinit var txtTotal: TextView
    private var porcentagem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        valor = binding.btValor
        seekBarEscala = binding.seekBarPorcentagem
        txtPorcentagem = binding.txtPorcentagem
        txtGorjeta = binding.txtGorjeta
        txtTotal = binding.txtTotal
        seekBarEscala.max = 100

        seekBarEscala.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Atualiza o texto com a porcentagem atual
                porcentagem = progress
                txtPorcentagem.text = "$porcentagem%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Lógica a ser executada quando o usuário toca no SeekBar
                calcularGorjeta()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Lógica a ser executada quando o usuário para de tocar no SeekBar
                calcularGorjeta()
            }
        })
        }

    private fun calcularGorjeta() {
        val valorDigitado = valor.text.toString().trim()

        if (valorDigitado.isNotEmpty()) {
            val valor = valorDigitado.toDouble()
            val porcentagem = seekBarEscala.progress
            val gorjeta = valor * porcentagem / 100
            val total = valor + gorjeta

            // Exibe a gorjeta calculada
            txtGorjeta.text = "R$ $gorjeta"
            txtTotal.text = "R$ $total"
        } else {
            Toast.makeText(applicationContext, "Informe um valor primeiro", Toast.LENGTH_SHORT).show()
        }
    }
}



package br.senai.sp.jandira.imc_app_b.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.senai.sp.jandira.imc_app_b.R

class ResultadoNcdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_ncd)

        val ncd = intent.getDoubleExtra("valor_ncd", 0.0)

        val textViewResultadoNcd = findViewById<TextView>(R.id.text_view_resultado_ncd)

        textViewResultadoNcd.text = String.format("%.0f", ncd)

    }

}
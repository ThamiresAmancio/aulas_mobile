package br.senai.sp.jandira.imc_app_b.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import br.senai.sp.jandira.imc_app_b.R

class NcdActivity : AppCompatActivity() {

    lateinit var editTextPeso: EditText
    lateinit var spinnerFaixaEtaria: Spinner
    lateinit var spinnerNivelAtividade: Spinner
    lateinit var radioFeminino: RadioButton
    lateinit var radioMasculino: RadioButton
    lateinit var buttonCalcularNcd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ncd)

        editTextPeso = findViewById(R.id.edit_text_peso)
        spinnerFaixaEtaria = findViewById(R.id.spinner_faixa_etaria)
        spinnerNivelAtividade = findViewById(R.id.spinner_nivel_atividade)
        radioFeminino = findViewById(R.id.radio_feminino)
        radioMasculino = findViewById(R.id.radio_masculino)
        buttonCalcularNcd = findViewById(R.id.button_calcular_ncd)

        buttonCalcularNcd.setOnClickListener {
            calcularNcd()
        }

    }

    fun calcularNcd() {

        val peso = editTextPeso.text.toString().toDouble()
        val faixaEtaria = spinnerFaixaEtaria.selectedItemPosition
        val nivelAtividade = spinnerNivelAtividade.selectedItemPosition
        var sexo = 'N'

        if (radioFeminino.isChecked || radioMasculino.isChecked) {
            if (radioFeminino.isSelected) sexo = 'F' else sexo = 'M'
        } else {
            Toast.makeText(this, "Selecione o sexo", Toast.LENGTH_SHORT).show()
        }

        val ncd = br.senai.sp.jandira.imc_app_b.calcularNcd(
            peso,
            faixaEtaria,
            nivelAtividade,
            sexo
        )

        val banana = Intent(this, ResultadoNcdActivity::class.java)
        banana.putExtra("valor_ncd", ncd)
        startActivity(banana)

    }

}
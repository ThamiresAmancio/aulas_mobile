package br.senai.sp.jandira.imc_app_b.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import br.senai.sp.jandira.imc_app_b.R
import br.senai.sp.jandira.imc_app_b.model.NivelAtividade
import kotlinx.android.synthetic.main.activity_biometria.*
import kotlinx.android.synthetic.main.toolbar.*

class BiometriaActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometria)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Dados biométricos"

        sb_nivel_atividade.setOnSeekBarChangeListener(this)
        tv_seek_nivel_atividade.text = NivelAtividade.LEVE.descricao
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (progress == 0) {
            tv_seek_nivel_atividade.text = NivelAtividade.LEVE.descricao
        } else if (progress == 1) {
            tv_seek_nivel_atividade.text = NivelAtividade.MODERADO.descricao
        } else {
            tv_seek_nivel_atividade.text = NivelAtividade.INTENSO.descricao
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_salvar) {
            salvarBiometria()
        }

        return true
    }

    private fun salvarBiometria() {
        Toast.makeText(this, "Biometria salva com sucesso!", Toast.LENGTH_SHORT).show()
    }
}
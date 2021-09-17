package br.senai.sp.jandira.imc_app_b.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.senai.sp.jandira.imc_app_b.R
import br.senai.sp.jandira.imc_app_b.dao.UsuarioDao
import br.senai.sp.jandira.imc_app_b.model.Usuario
import kotlinx.android.synthetic.main.activity_nova_conta.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class NovaContaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_conta)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Profile"

        // Calendário
        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        et_data_nascimento.setOnClickListener {
            // Cria o DatePickerDialog
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                et_data_nascimento.setText("$_dia/${_mes + 1}/$_ano")
            }, ano, mes, dia)

            // Mostra o DatePickerDialog
            dpd.show()
        }

//        // *** Criar spinner estilizado
//        val atividades = arrayListOf<String>("Nivel de atividade", "Leve", "Moderado", "Intenso")
//
//        var nivelAtividade: NivelAtividade
//
//        val arrayAdapter = ArrayAdapter<NivelAtividade>(
//            this,
//            R.layout.spinner_list,
//            NivelAtividade.values())
//
//        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
//        spinner_atividades.adapter = arrayAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_salvar) {
            salvarUsuario()
        }

        return true

    }

    private fun salvarUsuario() {
        val usuario = Usuario(
            0,
            et_email.text.toString(),
            et_senha.text.toString(),
            et_nome.text.toString(),
            et_profissao.editableText.toString(),
            et_data_nascimento.text.toString(),
            if (radio_feminino.isChecked) 'F' else 'M',
            et_altura.text.toString().toDouble(),
            null)

        val dao = UsuarioDao(this, usuario)
        dao.saveBasicInfo()
    }
}
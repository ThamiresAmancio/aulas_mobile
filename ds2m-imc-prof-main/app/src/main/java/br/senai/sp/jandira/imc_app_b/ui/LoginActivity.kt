package br.senai.sp.jandira.imc_app_b.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc_app_b.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_entrar.setOnClickListener {
//            val intent = Intent(this, DashboardActivity::class.java)
            val intent = Intent(this, BiometriaActivity::class.java)
            startActivity(intent)
        }

        tv_crie_sua_conta.setOnClickListener {
            val intent = Intent(this, NovaContaActivity::class.java)
            startActivity(intent)
        }
    }
}
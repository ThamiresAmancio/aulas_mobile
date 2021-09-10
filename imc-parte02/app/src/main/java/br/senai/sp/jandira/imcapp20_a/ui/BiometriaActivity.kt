package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imcapp20_a.R
import kotlinx.android.synthetic.main.activity_dash_board.*

class BiometriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometria)


        fun abrirDashBoard() {
            val intent = Intent(this, tv_pesar_agora::class.java)
            startActivity(intent)
            finish()
        }
    }
}
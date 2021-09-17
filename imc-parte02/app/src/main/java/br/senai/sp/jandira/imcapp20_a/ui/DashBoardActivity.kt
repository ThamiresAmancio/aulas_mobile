package br.senai.sp.jandira.imcapp20_a.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.R.id.tv_pesar_agora
import br.senai.sp.jandira.imcapp20_a.utils.NcdActivity
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64EmBitmap
import kotlinx.android.synthetic.main.activity_dash_board.*


class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        preencherDashBoard()

        tv_logout.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putBoolean("lembrar", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    //atributo da classe.
    private var alerta: AlertDialog? = null
    private fun criarDialog() {
        //Cria o gerador do AlertDialog
        val builder = AlertDialog.Builder(this)
        //define o titulo
        builder.setTitle("Preencha algumas informações")
        //define a mensagem
        builder.setMessage("Você não terminou o cadastro, deseja continuar ?")
        //define um botão como positivo
        builder.setPositiveButton(
            "Sim"
        ) { arg0, arg1 ->
            Toast.makeText(this@DashBoardActivity, "próxima página", Toast.LENGTH_SHORT).show()

        }
        //define um botão como negativo.
        builder.setNegativeButton(
            "Não"
        ) { arg0, arg1 ->
            Toast.makeText(this@DashBoardActivity, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        //cria o AlertDialog
        alerta = builder.create()
        //Exibe
        builder.show()
    }
    private fun preencherDashBoard() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")

        tv_weight.text = dados.getInt("peso",0).toString()
        tv_age.text = dados.getString("idade","").toString()

        val imagemBase64 = dados.getString("foto","")
        val imagemBitmap = converterBase64EmBitmap(imagemBase64)

        iv_profile.setImageBitmap(imagemBitmap)

        if (dados.getInt("peso",0) == 0){
            criarDialog()
        }
    }

    
    private  fun pesarAgora (){
        tv_pesar_agora.setOnClickListener {
            val intent = Intent(this, BiometriaActivity::class.java)
            startActivity(intent)
        }
    }
}

package br.senai.sp.jandira.imcapp20_a.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import org.jetbrains.anko.db.NULL
import java.util.*

// CRIADO SEMPRE ANTES DA CLASS, E O VALOR COLOCADO DENTRO DESSA VARIAVÉL NÃO MUDA
const val CODE_IMAGEM = 100

class NovoUsuarioActivity : AppCompatActivity() {

    var imageBitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        tv_trocar_foto.setOnClickListener {
            abrirGaleria()
        }

        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir um componente DatePickerDialog

        et_data_nascimento.setOnClickListener{

            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {view,_ano,_mes,_dia ->
                    var diaZero = "$_dia"
                    var mesZero = "$_mes"
                    if(_dia < 9){
                        diaZero = "0$_dia"
                    }

                    if(_mes < 9){
                        mesZero = "0${_mes + 1}"
                    }
                et_data_nascimento.setText("$diaZero/${mesZero}/$_ano")
            },ano,mes,dia)
            dpd.show()
        }

        bt_gravar.setOnClickListener {
            // *** Criar o sharedPreferences
//            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
//
//            val editor = dados.edit()
//            editor.putString("nome", et_nome.text.toString())
//            editor.putString("profissao", et_profissao.text.toString())
//            editor.putInt("peso", et_peso.text.toString().toInt())
//            editor.putInt("idade", et_data_nascimento.text.toString().toInt())
//            editor.putString("email", et_email.text.toString())
//            editor.putString("senha", et_senha.text.toString())
//            editor.apply()

            //** gravar o novo usuário no banco de dados SQLITE
            val usuario = Usuario(0,
                et_email.text.toString(),
                et_senha.text.toString(),
                et_nome.text.toString(),
                et_profissao.text.toString(),
                et_altura.text.toString().toDouble(),
                et_data_nascimento.text.toString(),
                'M',
                imageBitmap)

            val dao = UsuarioDao(this,usuario)
            dao.gravar()
            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun abrirGaleria() {
        // chamando a galeria das imagens
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        //define qual tipo de conteúdo deverá ser obtido
        intent.type = "image/*"
        //iniciar a Activity, mas neste caso só queremos que
        // está Activity retorne algo pra gente, a imagem
        startActivityForResult(Intent.createChooser(intent,"Escola uma foto"), CODE_IMAGEM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("PHOTO_GALERY","RESULT_CODE = $resultCode REQUEST_CODE = $requestCode")

        if(requestCode == CODE_IMAGEM && resultCode == -1){

            // Recuperar a imagem no stream
            val stream = contentResolver.openInputStream(data!!.data!!)
            // Transformar o resultado em bitmap
            imageBitmap = BitmapFactory.decodeStream(stream)

            img_profile.setImageBitmap(imageBitmap)
        }
    }
}
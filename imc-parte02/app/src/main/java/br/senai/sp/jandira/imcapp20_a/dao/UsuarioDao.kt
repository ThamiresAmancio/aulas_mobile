package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.ui.DashBoardActivity
import br.senai.sp.jandira.imcapp20_a.ui.LoginActivity
import br.senai.sp.jandira.imcapp20_a.utils.converterBitmapParaByteArray
import br.senai.sp.jandira.imcapp20_a.utils.obterDiferencaEntreDatasEmAnos
import kotlinx.android.synthetic.main.activity_dash_board.*
import java.time.LocalDate
import java.time.Period

class UsuarioDao(val context: Context, val usuario: Usuario?) {
    val dbHelper = ImcDataBase.getDatabase(context)
    public fun gravar() {
        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase
        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("nome", usuario!!.nome)
        dados.put("profissao", usuario.profissao)
        dados.put("email", usuario.email)
        dados.put("senha", usuario.senha)
        dados.put("altura", usuario.altura)
        dados.put("data_nascimento", usuario.dataNascimento.toString())
        dados.put("sexo", usuario.sexo.toString())
        dados.put("foto", converterBitmapParaByteArray(usuario.foto))


        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)
        db.close()
    }



    fun autenticar(email:String,senha:String) : Boolean {
        //*Obter um instânica de leitura do banco
        val db = dbHelper.readableDatabase
        //**** Determine quais são as colunas da tabela
        //**** que nós queremos no resultado
        //**** Vamos criar uma projeção
        val campos = arrayOf("email", "senha", "nome", "profissao", "data_nascimento","foto")
        //*** vamos definir p filtro da consulta
        // O que estamos fazendo é construir o filtro
        //  WHERE email = :  AND email = ?
        val filtro = "email = ? AND senha = ? "
        // Vamos criar agora os argumentos
        // vamos dizer ao kotli quais serão os valores
        // que deverão ser sibstituídos pela "?" no filtro
        val argumentos = arrayOf(email, senha)
        // Executar as consultas e obter os resultados em um cursor
        val cursor = db.query(
            "tb_usuario",
            campos,
            filtro,
            argumentos,
            null,
            null,
            null
        )
        Log.i("XPTO", "Linhas : ${cursor.count.toString()}")
        //*** Guardar a quantidade de linhas obtidas na consulta
        val linhas = cursor.count
        var autenticado = false


        if (linhas > 0) {
            autenticado = true
            cursor.moveToFirst()
            val emailIndex = cursor.getColumnIndex("email")
            val nomeIndex = cursor.getColumnIndex("nome")
            val profissaoIndex = cursor.getColumnIndex("profissao")
            val dataNascimentoIndex = cursor.getColumnIndex("data_nascimento")
            val dataNascimento = cursor.getString(dataNascimentoIndex)


            // criação/atualização
            //utilizando no restante da aplicação
            val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putString("nome",cursor.getString(nomeIndex))
            editor.putString("email",cursor.getString(emailIndex))
            editor.putString("profissao",cursor.getString(profissaoIndex))
            editor.putString("idade", obterDiferencaEntreDatasEmAnos(dataNascimento))
            editor.putInt("peso",0)
            editor.apply()
        }


        var hoje:LocalDate = LocalDate.now()
        var nascimento = LocalDate.of(1974,5,30)

        var idade = Period.between(nascimento,hoje)
        Log.i("XPTO",idade.years.toString())

        db.close()
        return  autenticado
    }

}
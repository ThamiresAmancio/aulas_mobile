package br.senai.sp.jandira.imc_app_b.dao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.senai.sp.jandira.imc_app_b.model.Biometria
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BiometriaDao(val context: Context, val biometria: Biometria) {

    val dbHelper = ImcDataBase.getDataBase(context)

    public fun gravarBiometria() {

        // *** Pegar instância do banco de dados
        val db = dbHelper.writableDatabase

        // *** Criar um mapa com os valores
        val values = ContentValues().apply {
            put("id_usuario", biometria.usuario?.id)
            put("peso", biometria.peso)
            put("nivel_atividade", biometria.nivelAtividade.name)
            put("data", formataData(LocalDate.now()))
        }

        // *** Inserir a nova linha retornando o id do novo usuário
        val novoUsuarioId = db.insert("tb_pesagem", null, values)

        db.close()

    }

    fun formataData(data: LocalDate) : String {
        val dt = LocalDate.now()
        val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return df.format(dt)
    }

}
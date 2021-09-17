package br.senai.sp.jandira.imc_app_b.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imc_app_b.model.Usuario

class UsuarioDao(context: Context, val usuario: Usuario) {

    val dbHelper = ImcDataBase.getDataBase(context)

    public fun saveBasicInfo() {

        // *** Pegar instância do banco de dados para escrita
        val db = dbHelper.writableDatabase

        // *** Criar um mapa com os valores
        val values = ContentValues().apply {
            put("email", usuario.email)
            put("senha", usuario.senha)
            put("nome", usuario.nome)
            put("profissao", usuario.profissao)
            put("data_nascimento", usuario.dataNascimento)
            put("altura", usuario.altura)
            put("sexo", usuario.sexo.toString())
        }

        // *** Inserir a nova linha retornando o id do novo usuário
        val novoUsuarioId = db.insert("tb_usuario", null, values)

        db.close()

    }

    public fun autenticar(email: String, senha: String): Usuario? {

        // *** Pegar instância do banco de dados para leitura
        val db = dbHelper.readableDatabase

        val sql = "SELECT * FROM tb_usuario WHERE email = ? AND senha = ?"

        val selectionArgs = arrayOf(email, senha)

        val resultado = db.rawQuery(sql, selectionArgs)

        if (resultado.moveToNext()) {
            var _usuario = Usuario(
                resultado.getInt(resultado.getColumnIndex("id")),
                resultado.getString(resultado.getColumnIndex("email")),
                resultado.getString(resultado.getColumnIndex("senha")),
                resultado.getString(resultado.getColumnIndex("nome")),
                resultado.getString(resultado.getColumnIndex("profissao")),
                resultado.getString(resultado.getColumnIndex("dataNascimento")),
                resultado.getString(resultado.getColumnIndex("sexo")) as Char,
                resultado.getDouble(resultado.getColumnIndex("altura")),
                null
            )

            return _usuario
        }

        return null

    }

}
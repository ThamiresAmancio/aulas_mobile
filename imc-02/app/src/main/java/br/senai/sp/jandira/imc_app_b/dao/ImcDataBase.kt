package br.senai.sp.jandira.imc_app_b.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

const val DATABASE_NAME = "imc.db"
const val VERSION = 2

class ImcDataBase(context: Context) : ManagedSQLiteOpenHelper(
    context, DATABASE_NAME, null, VERSION) {

    companion object {
        fun getDataBase(context: Context) : ImcDataBase {
            return ImcDataBase(context)
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        criarTabelaUsuario(db)
        criarTabelaBiometria(db)

    }

    private fun criarTabelaBiometria(db: SQLiteDatabase?) {
        // Criar tabela biometria
        val criarTabelaBiometria = "CREATE TABLE tb_pesagem (" +
                "id INTEGER PRIMARY KEY, " +
                "id_usuario INTEGER NOT NULL, " +
                "peso REAL NOT NULL, " +
                "nivel_atividade TEXT NOT NULL, " +
                "data TEXT NOT NULL)"

        db?.execSQL(criarTabelaBiometria)
    }

    private fun criarTabelaUsuario(db: SQLiteDatabase?) {
        // Criar tabela usuario
        val criarTabelaUsuario = "CREATE TABLE tb_usuario (" +
                "id INTEGER PRIMARY KEY," +
                "email TEXT," +
                "senha TEXT," +
                "nome TEXT," +
                "profissao TEXT," +
                "data_nascimento TEXT," +
                "altura REAL," +
                "sexo TEXT," +
                "foto BLOB)"

        db?.execSQL(criarTabelaUsuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS tb_usuario")
        db?.execSQL("DROP TABLE IF EXISTS tb_pesagem")

        criarTabelaUsuario(db)
        criarTabelaBiometria(db)
    }
}
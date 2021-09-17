package br.senai.sp.jandira.imc_app_b.model

import android.graphics.Bitmap

data class Usuario (
    var id: Int,
    var email: String,
    var senha: String,
    var nome: String,
    var profissao: String,
    var dataNascimento: String,
    var sexo: Char,
    var altura: Double,
    var foto: Bitmap? = null
)
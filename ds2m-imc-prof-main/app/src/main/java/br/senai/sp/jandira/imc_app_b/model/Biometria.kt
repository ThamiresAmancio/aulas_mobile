package br.senai.sp.jandira.imc_app_b.model

import java.time.LocalDate

data class Biometria (
    var id: Int,
    var peso: Double = 0.0,
    var nivelAtividade: NivelAtividade = NivelAtividade.LEVE,
    var data: LocalDate = LocalDate.now(),
    var usuario: Usuario? = null
)
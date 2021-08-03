package br.senai.sp.jandira.imc_app_b

fun calcularNcd(
    peso: Double,
    faixaEtaria: Int,
    nivelAtividade: Int,
    sexo: Char): Double {

    var ncd = 0.0

    // *** Taxa metabólica basal - TMB
    if (sexo == 'M') {
        if (faixaEtaria == 1) {
            ncd = 15.3 * peso + 679
        } else if (faixaEtaria == 2) {
            ncd = 11.6 * peso + 879
        } else {
            ncd = 13.5 * peso + 487
        }
    } else {
        if (faixaEtaria == 1) {
            ncd = 14.7 * peso + 496
        } else if (faixaEtaria == 2) {
            ncd = 8.7 * peso + 829
        } else {
            ncd = 10.5 * peso + 596
        }
    }

    // *** Considerar o nível de atividade da pessoa
    if (sexo == 'M') {
        if (nivelAtividade == 1) {
            ncd *= 1.5
        } else if (nivelAtividade == 2) {
            ncd *= 1.8
        } else {
            ncd *= 2.1
        }
    } else {
        if (nivelAtividade == 1) {
            ncd *= 1.6
        } else if (nivelAtividade == 2) {
            ncd *= 1.6
        } else {
            ncd *= 1.8
        }
    }

    return ncd
}
package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class BiometriaDao( val context: Context, val biometria: Biometria) {
    val dbHelper = ImcDataBase.getDatabase(context)
    public fun gravarBiometria() {
        val db = dbHelper.writableDatabase
        val dados = ContentValues()
           dados.put("id_usuario", biometria.usuario?.id)
            dados.put("peso", biometria.peso)
            dados.put("nivelAtiviade", biometria.nivelAtiviade)
            dados.put("data", getData(LocalDate.now()))

           val novoUsuarioDao=  db.insert("tb_biometria", null, dados)
            db.close()
        }
            fun getData(data : LocalDate): String {
                val dataAgora = LocalDate.now()
                val dataDepois = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                return  dataAgora.format(dataDepois)
        }
    }




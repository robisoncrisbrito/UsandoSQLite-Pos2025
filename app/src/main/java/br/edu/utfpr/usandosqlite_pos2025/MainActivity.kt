package br.edu.utfpr.usandosqlite_pos2025

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandosqlite_pos2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var banco : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.main )

        setButtonsListeneres()

        banco = SQLiteDatabase.openOrCreateDatabase(
            this.getDatabasePath( "dbfile.sqlite" ),
            null
        )

        banco.execSQL( "CREATE TABLE IF NOT EXISTS cadastro ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, telefone TEXT )" )
    }

    fun setButtonsListeneres() {
        binding.btIncluir.setOnClickListener {
            btIncluirOnClick()
        }

        binding.btAlterar.setOnClickListener {
            btAlterarOnClick()
        }

        binding.btExcluir.setOnClickListener {
            btExcluirOnClick()
        }

        binding.btPesquisar.setOnClickListener {
            btPesquisarOnClick()
        }

        binding.btListar.setOnClickListener {
            btListarOnClick()
        }
    }

    fun btIncluirOnClick() {

    }

    fun btAlterarOnClick() {

    }

    fun btExcluirOnClick() {

    }

    fun btPesquisarOnClick() {

    }

    fun btListarOnClick() {

    }

}
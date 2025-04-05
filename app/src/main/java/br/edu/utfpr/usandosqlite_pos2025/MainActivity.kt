package br.edu.utfpr.usandosqlite_pos2025

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandosqlite_pos2025.database.DatabaseHandler
import br.edu.utfpr.usandosqlite_pos2025.databinding.ActivityMainBinding
import br.edu.utfpr.usandosqlite_pos2025.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.main )

        setButtonsListeneres()

        banco = DatabaseHandler( this )
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
        val cadastro = Cadastro(
            0,
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.incluir( cadastro )

        Toast.makeText( this, "Registro incluído...", Toast.LENGTH_LONG ).show()
    }

    fun btAlterarOnClick() {

        val cadastro = Cadastro(
            binding.etCod.text.toString().toInt(),
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.alterar( cadastro )


        Toast.makeText( this, "Registro Alterado...", Toast.LENGTH_LONG ).show()

    }

    fun btExcluirOnClick() {
        banco.excluir( binding.etCod.text.toString().toInt() )

        Toast.makeText( this, "Registro Excluído...", Toast.LENGTH_LONG ).show()
    }

    fun btPesquisarOnClick() {
        val cadastro = banco.pesquisar( binding.etCod.text.toString().toInt() )

        if ( cadastro != null ) {
            binding.etNome.setText( cadastro.nome )
            binding.etTelefone.setText( cadastro.telefone )
        } else {
            Toast.makeText( this, "Registro não encontrado", Toast.LENGTH_LONG ).show()
        }



    }

    fun btListarOnClick() {

        val registros = banco.listar()

        val saida = StringBuilder()

        while ( registros.moveToNext() ) {
            saida.append( registros.getString( NOME ) )
            saida.append( " - " )
            saida.append( registros.getString( TELEFONE ) )
            saida.append( "\n" )
        }

        Toast.makeText( this, saida.toString(), Toast.LENGTH_LONG ).show()

    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }

} //fim da Activity


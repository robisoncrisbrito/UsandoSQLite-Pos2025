package br.edu.utfpr.usandosqlite_pos2025

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
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
        val registro = ContentValues()
        registro.put( "nome", binding.etNome.text.toString() )
        registro.put( "telefone", binding.etTelefone.text.toString() )
        
        banco.insert( "cadastro", null, registro )

        Toast.makeText( this, "Registro incluído...", Toast.LENGTH_LONG ).show()
    }

    fun btAlterarOnClick() {
        val registro = ContentValues()
        registro.put( "nome", binding.etNome.text.toString() )
        registro.put( "telefone", binding.etTelefone.text.toString() )

        banco.update(
            "cadastro",
            registro,
            "_id=${binding.etCod.text.toString()}",
            null
        )

        Toast.makeText( this, "Registro Alterado...", Toast.LENGTH_LONG ).show()

    }

    fun btExcluirOnClick() {
        banco.delete( "cadastro", "_id=${binding.etCod.text.toString()}", null )

        Toast.makeText( this, "Registro Excluído...", Toast.LENGTH_LONG ).show()
    }

    fun btPesquisarOnClick() {
        val registros = banco.query( "cadastro", null, "_id=?", arrayOf(binding.etCod.text.toString() ), null, null, null )

        if ( registros.moveToNext() ) {
            binding.etNome.setText( registros.getString( NOME ) )
            binding.etTelefone.setText( registros.getString( TELEFONE ) )
        } else {
            Toast.makeText( this, "Registro não encontrado", Toast.LENGTH_LONG ).show()
        }



    }

    fun btListarOnClick() {

        val registros = banco.query( "cadastro", null, null, null, null, null, null )

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


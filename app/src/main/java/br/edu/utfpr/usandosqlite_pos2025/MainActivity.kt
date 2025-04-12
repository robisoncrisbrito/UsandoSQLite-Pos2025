package br.edu.utfpr.usandosqlite_pos2025


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        initData()

        banco = DatabaseHandler( this )
    }

    private fun initData() {

        if ( intent.getIntExtra( "cod", 0 ) != 0 ) {
            binding.etCod.setText(intent.getIntExtra("cod", 0).toString())
            binding.etNome.setText(intent.getStringExtra("nome"))
            binding.etTelefone.setText(intent.getStringExtra("telefone"))
        }

    }

    fun setButtonsListeneres() {

        binding.btAlterar.setOnClickListener {
            btAlterarOnClick()
        }

        binding.btExcluir.setOnClickListener {
            btExcluirOnClick()
        }

        binding.btPesquisar.setOnClickListener {
            btPesquisarOnClick()
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
        val intent = Intent( this, ListarActivity::class.java )
        startActivity( intent )
/*
        val registros = banco.listar()

        val saida = StringBuilder()

        while ( registros.moveToNext() ) {
            saida.append( registros.getString( NOME ) )
            saida.append( " - " )
            saida.append( registros.getString( TELEFONE ) )
            saida.append( "\n" )
        }

        Toast.makeText( this, saida.toString(), Toast.LENGTH_LONG ).show()
*/
    }

} //fim da Activity


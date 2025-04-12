package br.edu.utfpr.usandosqlite_pos2025


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        } else {
            binding.btPesquisar.visibility = View.GONE
            binding.btExcluir.visibility = View.GONE
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
    }

    fun btAlterarOnClick() {

        if ( binding.etCod.text.toString().isEmpty() ) {
            val cadastro = Cadastro(
                0,
                binding.etNome.text.toString(),
                binding.etTelefone.text.toString()
            )

            banco.incluir( cadastro )

            Toast.makeText( this, "Registro incluído...", Toast.LENGTH_LONG ).show()

            finish()
        } else {
            val cadastro = Cadastro(
                binding.etCod.text.toString().toInt(),
                binding.etNome.text.toString(),
                binding.etTelefone.text.toString()
            )

            banco.alterar(cadastro)

            Toast.makeText( this, "Registro Alterado...", Toast.LENGTH_LONG ).show()

            finish()
        }



    }

    fun btExcluirOnClick() {
        banco.excluir( binding.etCod.text.toString().toInt() )

        Toast.makeText( this, "Registro Excluído...", Toast.LENGTH_LONG ).show()

        finish()
    }

    fun btPesquisarOnClick() {

        val builder = AlertDialog.Builder( this )
        builder.setTitle( "Código da pessoa" )
        val etCodPesquisa = EditText( this )
        builder.setView( etCodPesquisa )
        builder.setCancelable( false )
        builder.setNegativeButton( "Fechar", null )
        builder.setPositiveButton( "Pesquisar", { dialogInterface, i ->
            val cadastro = banco.pesquisar( etCodPesquisa.text.toString().toInt() )

            if ( cadastro != null ) {
                binding.etCod.setText(etCodPesquisa.text.toString() )
                binding.etNome.setText( cadastro.nome )
                binding.etTelefone.setText( cadastro.telefone )
            } else {
                Toast.makeText( this, "Registro não encontrado", Toast.LENGTH_LONG ).show()
            }

        } )
        builder.show()



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


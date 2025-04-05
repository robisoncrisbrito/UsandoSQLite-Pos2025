package br.edu.utfpr.usandosqlite_pos2025

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandosqlite_pos2025.adapter.MeuAdapter
import br.edu.utfpr.usandosqlite_pos2025.database.DatabaseHandler
import br.edu.utfpr.usandosqlite_pos2025.databinding.ActivityListarBinding
import br.edu.utfpr.usandosqlite_pos2025.databinding.ActivityMainBinding

class ListarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListarBinding

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate( layoutInflater )
        setContentView( binding.root )

        banco = DatabaseHandler( this )

        val registros = banco.listar()

        val adapter = MeuAdapter( this, registros )

        binding.lvPrincipal.adapter = adapter

    }
}
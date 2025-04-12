package br.edu.utfpr.usandosqlite_pos2025.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import br.edu.utfpr.usandosqlite_pos2025.MainActivity
import br.edu.utfpr.usandosqlite_pos2025.R
import br.edu.utfpr.usandosqlite_pos2025.database.DatabaseHandler
import br.edu.utfpr.usandosqlite_pos2025.entity.Cadastro

class MeuAdapter(var context : Context, var cursor : Cursor) : BaseAdapter() {

    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(pos: Int): Any {
        cursor.moveToPosition( pos )
        val cadastro = Cadastro(
            cursor.getInt( DatabaseHandler.ID ).toInt(),
            cursor.getString( DatabaseHandler.NOME ).toString(),
            cursor.getString( DatabaseHandler.TELEFONE ).toString()
        )
        return cadastro
    }

    override fun getItemId(pos: Int): Long {
        cursor.moveToPosition( pos )
        return cursor.getInt( DatabaseHandler.ID ).toLong()
    }

    override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {
        val inflater =
            context.getSystemService( Context.LAYOUT_INFLATER_SERVICE )
                    as LayoutInflater

        val elementoLista = inflater.inflate( R.layout.elemento_lista, null )

        val tvNomeElementoLista = elementoLista.findViewById<TextView>( R.id.tvNomeElementoLista )
        val tvTelefoneElementoLista = elementoLista.findViewById<TextView>( R.id.tvTelefoneElementoLista )
        val btEditarElementoLista = elementoLista.findViewById<ImageButton>(R.id.btEditarElementoLista)

        cursor.moveToPosition( pos )

        tvNomeElementoLista.text = cursor.getString( DatabaseHandler.NOME )
        tvTelefoneElementoLista.text = cursor.getString( DatabaseHandler.TELEFONE )

        btEditarElementoLista.setOnClickListener{
            cursor.moveToPosition( pos )

            val intent = Intent( context, MainActivity::class.java )
            intent.putExtra( "cod", cursor.getInt( DatabaseHandler.ID ) )
            intent.putExtra( "nome", cursor.getString( DatabaseHandler.NOME ) )
            intent.putExtra( "telefone", cursor.getString( DatabaseHandler.TELEFONE ) )
            context.startActivity( intent )
        }

        return elementoLista

    }
}
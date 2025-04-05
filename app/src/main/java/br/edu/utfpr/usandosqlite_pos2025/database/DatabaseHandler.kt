package br.edu.utfpr.usandosqlite_pos2025.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.utfpr.usandosqlite_pos2025.entity.Cadastro

class DatabaseHandler( context : Context) :
    SQLiteOpenHelper( context, DATABASE_NAME, null, DATABASE_VERSION ) {

    override fun onCreate(banco: SQLiteDatabase?) {
        banco?.execSQL( "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, telefone TEXT )" )
    }

    override fun onUpgrade(banco: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        banco?.execSQL( "DROP TABLE IF EXISTS ${TABLE_NAME}" )
        onCreate( banco )
    }

    fun incluir( cadastro : Cadastro ) {
        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put( "nome", cadastro.nome )
        registro.put( "telefone", cadastro.telefone )

        banco.insert( "cadastro", null, registro )
    }

    fun alterar( cadastro : Cadastro ) {
        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put( "nome", cadastro.nome )
        registro.put( "telefone", cadastro.telefone )

        banco.update(
            "cadastro",
            registro,
            "_id=${cadastro._id}",
            null
        )
    }

    fun excluir( id : Int ) {
        val banco = this.writableDatabase
        banco.delete( "cadastro", "_id=${id}", null )
    }

    fun pesquisar( id : Int ) : Cadastro? {
        val banco = this.writableDatabase

        val registro = banco.query(
            "cadastro",
            null,
            "_id=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if ( registro.moveToNext() ) {
            val cadastro = Cadastro (
                id,
                registro.getString( NOME ),
                registro.getString( TELEFONE )
            )
            return cadastro
        } else {
            return null
        }
    }

    fun listar() : Cursor {
        val banco = this.writableDatabase

        val registros = banco.query(
            "cadastro",
            null,
            null,
            null,
            null,
            null,
            null
        )

        return registros
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "dbfile.sqlite"
        private const val TABLE_NAME = "cadastro"
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }

} //fim do database handler
package com.example.abk.tps;

/**
 * Created by abakarim Marouane on 27/09/2017.
 */

import android.content.*;
import android.database.sqlite.*;

import android.database.sqlite.SQLiteDatabase.*;

public class MaBaseSQlite extends SQLiteOpenHelper{


    private static final String TABLE_NAME = "table_repertoires";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PRENOM = "PRENOM";
    private static final String COL_TEL = "TEL";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_ADDRESS = "ADDRESS";
    private static final String COL_COMMENTAIRE = "COMMENTAIRE";
    /*private static final String CREATE_BDD = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL, "
            + COL_PRENOM + " TEXT NOT NULL, "
            + COL_TEL + " TEXT NOT NULL, "
            + COL_EMAIL + " TEXT NOT NULL, "
            + COL_ADDRESS + " TEXT NOT NULL, "
            + COL_COMMENTAIRE +" TEXT NOT NULL );";*/



    public MaBaseSQlite(Context context,String name,CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }

    public void onCreate(SQLiteDatabase db) {
        /*La creation de la table Repertoire
            CREATE_BDD contient la définitions de la table
        */
         db.execSQL(CREATE_BDD);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_NAME + ";");
        onCreate(db);
    }
}

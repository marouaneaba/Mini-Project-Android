package com.example.abk.tps;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;


/**
 * Created by abakarim marouane on 27/09/2017.
 * Email : Mar.abakarim@gmail.com
 */

public class RepertoireBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "personne.db";

    private static final String TABLE_PERSONNES = "table_personne";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_PRENOM = "PRENOM";
    private static final int NUM_COL_PRENOM = 2;
    private static final String COL_TEL = "TEL";
    private static final int NUM_COL_TEL = 3;
    private static final String COL_EMAIL = "EMAIL";
    private static final int NUM_COL_EMAIL = 4;
    private static final String COL_ADDRESS = "ADDRESS";
    private static final int NUM_COL_ADDRESS = 5;
    private static final String COL_COMMENTAIRE = "COMMENTAIRE";
    private static final int NUM_COL_COMMENTAIRE = 6;


    private SQLiteDatabase bdd;
    private MaBaseSQlite maBaseSQLite;



    /**/

    public RepertoireBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new MaBaseSQlite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }


    public void insertPersonne(Personne personne){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, personne.getId());
        values.put(COL_NAME, personne.getName());
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_TEL,personne.getTel());
        values.put(COL_EMAIL,personne.getEmail());
        values.put(COL_ADDRESS,personne.getAddress());
        values.put(COL_COMMENTAIRE,personne.getCommentaire());
        //on insère l'objet dans la BDD via le ContentValues

        bdd.insert(TABLE_PERSONNES, null, values);
    }

    public Cursor getAllData(){
        Cursor res = bdd.query(TABLE_PERSONNES, new String[] {COL_ID, COL_NAME, COL_PRENOM,COL_TEL,
                COL_EMAIL,COL_ADDRESS,COL_COMMENTAIRE},  null, null, null, null,null);
        return res;
    }


    private Personne cursorToPersonne(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        Personne personne = new Personne();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        personne.setId(c.getInt(NUM_COL_ID));
        personne.setName(c.getString(NUM_COL_NAME));
        personne.setPrenom(c.getString(NUM_COL_PRENOM));
        personne.setTel(c.getString(NUM_COL_TEL));
        personne.setEmail(c.getString(NUM_COL_EMAIL));
        personne.setAddress(c.getString(NUM_COL_ADDRESS));
        personne.setCommentaire(c.getString(NUM_COL_COMMENTAIRE));

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return personne;
    }

}

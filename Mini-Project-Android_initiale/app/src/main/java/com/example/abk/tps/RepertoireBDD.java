package com.example.abk.tps;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;


/**
 * Created by abakarim marouane on 27/09/2017.
 */

public class RepertoireBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "personne.db";

    private static final String TABLE_PERSONNES = "table_repertoires";
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



    /* Cration la BDD et la table*/

    public RepertoireBDD(Context context){
        //On créer la BDD et sa table
         maBaseSQLite = new MaBaseSQlite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        /* TODO */
    }

    public void close(){
        //on ferme l'accès à la BDD
        /* TODO */
    }

    public SQLiteDatabase getBDD(){
        /* méthode retourne notre bases de données */
        return bdd;
    }

    /* insérer un personne dans notre base de données */
    public void insertPersonne(Personne personne){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        /* TODO */


        //on insère l'objet dans la BDD via le ContentValues
       /* bdd.insert(TABLE_PERSONNES, null, values); */
    }

    /* récupérer tout les données de notre bases de données */
    public Cursor getAllData(){
        Cursor res = bdd.query(TABLE_PERSONNES, new String[] {COL_ID, COL_NAME, COL_PRENOM,COL_TEL,
                COL_EMAIL,COL_ADDRESS,COL_COMMENTAIRE},  null, null, null, null,null);
        return res;
    }

    /* retourner un personne à partir son id */
    public Personne getPersonneById(int id)
    {
        /* TODO */
        String nom_table = ;
        String[] Column =;
        String Where = ;
        Cursor c = bdd.query(nom_table,Column,Where,null,null,null,null);
        return cursorToPersonne(c);
    }

    /* retourner un personne à partir un mot-clé (nom ,prénom ,Tél, Email, Address,Commentaire )*/
    public Cursor getPersonneByOther(String name,String prenom,String tel,String email,String address,String commentaire)
    {
        String selectQuery = "TODO";
        /* TODO */
         

        // on met le résultat dans un Cursor
        Cursor c = bdd.rawQuery(selectQuery, null);

        return c;
    }

    /* transformer un cursor en List de personne */
    public List<Map<String, String>> CursorToVectorPersonne(Cursor c){


        /* passé au le premiére ligne */
        c.moveToFirst();
        List<Map<String, String>> listOfPersonne_i = new ArrayList<Map<String, String>>();


        /* TODO */

        return listOfPersonne_i;
    }

    /* supprimer un personne à partir son id */
    public void SupprimerWithId(int id){
        /* TODO */
    }

    /* modifier un personne à partir son id */
    public int updateById(int id,Personne personne){
        //La mise à jour d'un Contact dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle contact on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        /* values.put(COL_NAME, personne.getName()); */
        /* TODO */
        return bdd.update(TABLE_PERSONNES,values, COL_ID + "=" +id, null);
    }

    /* transformer le premiére ligne de cursor en personne */
    public Personne cursorToPersonne(Cursor c){

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
        //c.close();

        //On retourne le livre
        return personne;
    }

}
package com.example.abk.tps;

/**
 * Created by abk on 29/09/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import java.util.*;

public class Visualiser extends AppCompatActivity {


    private ListView RepertoireList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualiser);


        // Recherche la vue affichant la liste
        RepertoireList = (ListView) findViewById(R.id.ContactList);

         /* création d'une instance de la classe RepertoireBDD */
        RepertoireBDD repertoireBDD = new RepertoireBDD(this);

        /* on ouvre la base de données pour écrire dedans */
        repertoireBDD.open();

        /* on insér le contact dans la base de données */


        Cursor c = repertoireBDD.getAllData();

        /* montrez aù premier ligne de notre BD */
        c.moveToFirst();


        List<Map<String, String>> listOfPersonne_i = new ArrayList<Map<String, String>>();

        while(c.moveToNext()){

            Personne p = new Personne(c.getString(1),c.getString(2),
                    c.getString(3),c.getString(4),
                    c.getString(5),c.getString(6));

            Map<String, String> bookMap_i = new HashMap<String, String>();
            bookMap_i.put("Name", c.getString(1));
            bookMap_i.put("Prenom", c.getString(2));
            bookMap_i.put("Tel", c.getString(3));
            bookMap_i.put("Email", c.getString(4));
            bookMap_i.put("Adderess", c.getString(5));
            bookMap_i.put("Commentaire", c.getString(6));

            listOfPersonne_i.add(bookMap_i);

        }



        repertoireBDD.close();

        // Cree un adapter faisant le lien entre la liste d'élément et la ListView servant à l'affichage.
        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), listOfPersonne_i, R.layout.personne_detail,
                new String[] {"Name","Prenom","Email"},
                new int[] { R.id.Name,R.id.Prenom, R.id.Email});
        //Associe l’adapter et le ListView
        RepertoireList.setAdapter(listAdapter);

        RepertoireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) RepertoireList.getItemAtPosition(position);
                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(Visualiser.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle("Sélection Item");
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("Votre choix : "+map.get("Name"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.show();
            }
        });
        

    }

}

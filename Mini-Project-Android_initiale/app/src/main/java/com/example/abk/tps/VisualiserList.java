package com.example.abk.tps;

/**
 * Created by Marouane Abakarim on 29/09/2017.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import java.util.*;

public class VisualiserList extends AppCompatActivity {


    private ListView RepertoireList;
    private FragmentManager fm = getSupportFragmentManager();
    private RepertoireBDD repertoireBDD;



    @Override
    public void onResume(){
        super.onResume();
        /* Aprés la pause de l'activité et avant de relancé le fonctionnment de l'activité
         * faut mettre à jour les données on veut l'afficher  */
        Actualiser();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        /* lorsque que l'activité a fini son cycle de vie
        * la base de données ne serait pas utiliser ,
        * faut fermer notre base de données*/
        repertoireBDD.close();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        /* avant de démarer l'activité
        * on peux ouvrire notre base de données pour écrire dedans */
        repertoireBDD.open();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualiser_list);


        // Recherche la vue affichant la liste
        RepertoireList = (ListView) findViewById(R.id.ContactList);

         // création d'une instance de la classe RepertoireBDD
        repertoireBDD = new RepertoireBDD(this);

        // on ouvre la base de données pour écrire dedans
        repertoireBDD.open();


        /* on récuper les données à partir la base de données */
        Actualiser();
        /* si note base de données et vide ,informe l'utilisateur aucun Personne enregister */
        if(repertoireBDD.getAllData().getCount() == 0){
            showMessage("Liste de Contact", "Votre List est Vide");
        }

            /* on met un écouteur d'évènement sur notre listView "Long Clique"  */
        RepertoireList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                final HashMap<String, String> map = (HashMap<String, String>) RepertoireList.getItemAtPosition(position);



                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(VisualiserList.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle("Contact Sélection : "+map.get("Name")+" "+map.get("Prenom"));
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("voulez vous Vraiment suprimer se contact");
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "clique OK : "+map.get("ID"), Toast.LENGTH_SHORT).show();
                        repertoireBDD.SupprimerWithId(Integer.parseInt(map.get("ID")));
                        Actualiser();
                    }

                });
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel button
                        Toast.makeText(getApplicationContext(), "clique Cancel : "+map.get("ID"), Toast.LENGTH_SHORT).show();
                    }
                });

                //on affiche la boite de dialogue
                adb.show();

                return true;
            }
        });

        //Enfin on met un écouteur d'évènement sur notre listView "Clique"
        RepertoireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) RepertoireList.getItemAtPosition(position);


                Intent intent = new Intent(VisualiserList.this, AfficherContact.class);
                intent.putExtra("numero",""+map.get("ID"));
                startActivity(intent);
            }
        });

        /* fermuture de la bases de données */
        repertoireBDD.close();
    }

    /* daire la Mise à jour des données on veut l'afficher à pertir l'identifiant de personne */
    public void Actualiser(){

        Cursor c = repertoireBDD.getAllData();

        // montrez aù premier ligne de notre BD
        c.moveToFirst();

        List<Map<String, String>> listOfPersonne_i = new ArrayList<Map<String, String>>();

        while(!c.isAfterLast()){

            Personne p = new Personne(c.getString(1),c.getString(2),
                    c.getString(3),c.getString(4),
                    c.getString(5),c.getString(6));

            Map<String, String> bookMap_i = new HashMap<String, String>();
            bookMap_i.put("ID", c.getString(0));
            bookMap_i.put("Name", c.getString(1));
            bookMap_i.put("Prenom", c.getString(2));
            bookMap_i.put("Tel", c.getString(3));
            bookMap_i.put("Email", c.getString(4));
            bookMap_i.put("Adderess", c.getString(5));
            bookMap_i.put("Commentaire", c.getString(6));
            listOfPersonne_i.add(bookMap_i);

            c.moveToNext();
        }





        // Cree un adapter faisant le lien entre la liste d'élément et la ListView servant à l'affichage.
        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), listOfPersonne_i, R.layout.personne_detail,
                new String[] {"Name","Prenom","Email"},
                new int[] { R.id.Name,R.id.Prenom, R.id.Email});
        //Associe l’adapter et le ListView
        RepertoireList.setAdapter(listAdapter);

    }

    /* afficher les notification  */
    void showMessage(String title, String message){
        android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

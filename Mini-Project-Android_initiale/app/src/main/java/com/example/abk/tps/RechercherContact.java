package com.example.abk.tps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marouane Abakarim on 01/10/2017.
 */

public class RechercherContact extends AppCompatActivity {


    private RepertoireBDD repertoireBDD;
    private ListView listView;
    private TextView textView;
    private EditText editTexte;

    private String texte;

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
        * faut fermer notre base de données */
/************** repertoireBDD.close(); */
    }

    @Override
    public void onStart()
    {
        super.onStart();
      /* avant de démarer l'activité
        * on peux ouvrire notre base de données pour écrire dedans */
 /*********** repertoireBDD.open(); */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_contact);


        listView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textView);
        editTexte = (EditText)findViewById(R.id.editText);

        texte = textView.getText().toString();

        repertoireBDD = new RepertoireBDD(this);
        repertoireBDD.open();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                final HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);

                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(RechercherContact.this);
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

        //Enfin on met un écouteur d'évènement sur notre listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "le nombre : "+map.get("ID"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RechercherContact.this, AfficherContact.class);

                intent.putExtra("numero",""+map.get("ID"));
                startActivity(intent);
            }
        });

    }


    public void Rechercher(View v)
    {
        Actualiser();
    }

    /* faire une Mise à jour des données que on veut l'afficher */
    public void Actualiser(){

    if(!editTexte.getText().toString().equals("")) {
        Cursor c = repertoireBDD.getPersonneByOther(editTexte.getText().toString(), editTexte.getText().toString(),
                editTexte.getText().toString(), editTexte.getText().toString(),
                editTexte.getText().toString(), editTexte.getText().toString());


        List<Map<String, String>> list = repertoireBDD.CursorToVectorPersonne(c);

        // Cree un adapter faisant le lien entre la liste d'élément et la ListView servant à l'affichage.
        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), list, R.layout.personne_detail,
                new String[]{"Name", "Prenom", "Email"},
                new int[]{R.id.Name, R.id.Prenom, R.id.Email});
        //Associe l’adapter et le ListView
        listView.setAdapter(listAdapter);
    }

    }
}

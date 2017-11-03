package com.example.abk.tps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marouane abakarim  30/09/2017.
 */

public class AfficherContact extends AppCompatActivity {


        private TextView Name,Prenom,Tel,Email,Adress,Commentaire;
        private int id;
        private RepertoireBDD repertoireBDD;

    @Override
    public void onResume(){
        super.onResume();
        /* Aprés la pause de l'activité et avant de relancé le fonctionnment de l'activité
         * faut mettre à jour les données on veut l'afficher  */
        actualiser();
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
        setContentView(R.layout.afficher_contact);

        Name = (TextView)findViewById(R.id.LabelName);
        Prenom = (TextView)findViewById(R.id.LabelPrenom);
        Tel = (TextView)findViewById(R.id.LabelTel);
        Email = (TextView)findViewById(R.id.LabelEmail);
        Adress = (TextView)findViewById(R.id.LabelAddress);
        Commentaire = (TextView)findViewById(R.id.LabelCommentaire);


         /* création d'une instance de la classe RepertoireBDD */
        repertoireBDD = new RepertoireBDD(this);


        Intent intent = getIntent();
        //int position = Integer.parseInt(intent.getStringExtra("numero"));
        /* on récupere l'identifiant de personne on veut l'afficher */
        id= Integer.parseInt(intent.getStringExtra("numero"));
    


    }
    /* faire une Mise à jour des données on veut l'afficher à partir l'identifiant de Contact */
    public void actualiser(){

        Personne p = repertoireBDD.getPersonneById(id);

        Name.setText(p.getName());
        Prenom.setText(p.getPrenom());
        Tel.setText(p.getTel());
        Email.setText(p.getEmail());
        Adress.setText(p.getAddress());
        Commentaire.setText(p.getCommentaire());
    }

    /* quitter l'activité */
    public void Annuler(View v){
        /* aprés annulation de l'affichage ,on ferme l'activity*/
        finish();
    }

    /* modifier personne courant */
    public void Modifier(View v){

        /*on envoyé l'identifiant de personne on veut le modofier */
        Intent intent = new Intent(AfficherContact.this, ModifierContact.class);
        intent.putExtra("id",""+id);
        startActivity(intent);
    }
}

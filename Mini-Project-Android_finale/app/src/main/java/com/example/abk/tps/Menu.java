package com.example.abk.tps;

/**
 * Created by Marouane Abakarim on 29/09/2017.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button CreerContact = (Button)findViewById(R.id.ajout);
        Button VisualiserContact = (Button)findViewById(R.id.afficher);
        Button RechercherContact = (Button)findViewById(R.id.recherch);
        Button QuitterContact = (Button)findViewById(R.id.quitter);

    }

    /* insérer un nouveux Personne dans le répertoire */
    public void CreerMethode(View v){
        Intent intent = new Intent(Menu.this, InsertPersonne.class);
        startActivity(intent);
    }
    /* Visualiser tout les personne de notre base de données */
    public void VisualiserMethode(View v){
        Intent intent = new Intent(Menu.this, VisualiserList.class);
        startActivity(intent);
    }

    /* chercher un personne à partir d'un mot clé */
    public void RecherchMethode(View v){
        Intent intent = new Intent(Menu.this, RechercherContact.class);
        startActivity(intent);
    }

    /* quitter notre application */
    public void QuitterMethode(View v){
        finish();
    }


}

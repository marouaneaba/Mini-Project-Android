package com.example.abk.tps;

/**
 * Created by abk on 29/09/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ListView;

public class Visualiser extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualiser);


        // Recherche la vue affichant la liste
        ListView bookList = (ListView) findViewById(R.id.ContactList);

         /* création d'une instance de la classe RepertoireBDD */
        RepertoireBDD repertoireBDD = new RepertoireBDD(this);

        /* on ouvre la base de données pour écrire dedans */
        repertoireBDD.open();

        /* on insér le contact dans la base de données */
        

    }

}

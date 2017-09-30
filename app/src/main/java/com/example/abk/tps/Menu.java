package com.example.abk.tps;

/**
 * Created by abk on 29/09/2017.
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

    public void CreerMethode(View v){
        Intent intent = new Intent(Menu.this, InsertPersonne.class);
        startActivity(intent);
    }

    
    public void VisualiserMethode(View v){
        Intent intent = new Intent(Menu.this, VisualiserList.class);
        startActivity(intent);
    }

    public void RecherchMethode(View v){
        Intent intent = new Intent(Menu.this, RechercherContact.class);
        startActivity(intent);
    }

    public void QuitterMethode(View v){
        finish();
    }



/*
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        ((TextView)findViewById(R.id.texte)).setText("§ Menu §");
        //Création d'un MenuInflater qui va permettre d'instancier un Menu1 XML en un objet Menu1
        MenuInflater inflater = getMenuInflater();
        //Instanciation du com.example.afrass.firstapp.Menu1 XML spécifier en un objet Menu1
        inflater.inflate(R.menu.option, menu);
        return true;
    }


    @Override
    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à  son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.option:
                ((TextView)findViewById(R.id.texte)).setText("Vous avez cliqué sur option, veuillez attendre...");
                return true;
            case R.id.creerContact:
                Intent intent = new Intent(Menu.this, InsertPersonne.class);
                startActivity(intent);
                //((TextView)findViewById(R.id.texte)).setText("CreerUnLivre");
                return true;
            case R.id.visualiserContact:
                Intent intent2 = new Intent(Menu.this, VisualiserList.class);
                startActivity(intent2);
                //((TextView)findViewById(R.id.texte)).setText("VisualiserLivres");
                return true;
           // case R.id.RechercheContact :
            //    Intent intent3 = new Intent(Menu.this,rechercher.class);
              //  startActivity(intent3);
              //  return true;
            case R.id.quitter:

                ((TextView)findViewById(R.id.texte)).setText("Vous avez cliqué sur quitter,veuillez attendre...");
                //Pour fermer l'application il suffit de faire finish()
                finish();
                return true;
        }
        return false;
    }*/
}

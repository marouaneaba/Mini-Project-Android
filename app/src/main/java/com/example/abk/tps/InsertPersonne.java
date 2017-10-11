package com.example.abk.tps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.database.*;
import java.util.*;

/**
 * Created by Marouane abakarim  30/09/2017.
 */

public class InsertPersonne extends AppCompatActivity {

    private EditText NameEd;
    private EditText PrenomEd;
    private EditText TelEd;
    private EditText EmailEd;
    private EditText AddresseEd;
    private EditText CommentaireEd;

    private String name,prenom,tel,email,addresse,commentaire;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_personne);

        NameEd = (EditText)findViewById(R.id.nameeditText);
        PrenomEd = (EditText)findViewById(R.id.prenomeditText3);
        TelEd = (EditText)findViewById(R.id.numeroeditText9);
        EmailEd = (EditText)findViewById(R.id.emaileditText5);
        AddresseEd = (EditText)findViewById(R.id.adderesseeditText6);
        CommentaireEd = (EditText)findViewById(R.id.commentaireeditText7);


    }


    public void Enregistrer(View v)
    {

        /* création d'une instance de la classe LivreBDD */
        RepertoireBDD repertoireBdd = new RepertoireBDD(this);


        String tel = TelEd.getText().toString().replaceAll(" ","");

        Personne perosnne = new Personne(NameEd.getText().toString(),PrenomEd.getText().toString(),
                tel,EmailEd.getText().toString(),AddresseEd.getText().toString(),
                CommentaireEd.getText().toString());

        /* on ouvre la base de données pour écrire dedans */
        repertoireBdd.open();


        /* verification de la saisie */
        String add[] = EmailEd.getText().toString().split("@");
        if(!EmailEd.getText().toString().contains("@") || !add[1].contains(".")){
            Toast.makeText(getApplicationContext(), "veuillez saisie une bonne addresse Email ... ", Toast.LENGTH_SHORT).show();

        }/*else if(tel.startsWith("0") ||     tel.startsWith("+")){
            Toast.makeText(getApplicationContext(), "veuillez saisie un bonne Numéro de Tél .... ", Toast.LENGTH_SHORT).show();

        }*/else{
            /* on insert dans notre Base de données */
            repertoireBdd.insertPersonne(perosnne);
        }






        /* on insert dans notre Base de données */
        //repertoireBdd.insertPersonne(perosnne);



        repertoireBdd.close();
        finish();

    }

    void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void vider(View v)
    {
        Clear();
    }
    public void Clear()
    {
        NameEd.setText("");
        PrenomEd.setText("");
        TelEd.setText("");
        EmailEd.setText("");
        AddresseEd.setText("");
        CommentaireEd.setText("");
    }
}

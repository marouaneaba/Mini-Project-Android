package com.example.abk.tps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.*;
import android.content.Intent;
import android.view.*;
import android.widget.*;


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
        setContentView(R.layout.activity_main);

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

        Personne perosnne = new Personne(NameEd.getText().toString(),PrenomEd.getText().toString(),
                TelEd.getText().toString(),EmailEd.getText().toString(),AddresseEd.getText().toString(),
                CommentaireEd.getText().toString());

        /* on ouvre la base de données pour écrire dedans */
        repertoireBdd.open();

        /* on insert dans notre Base de données */
        repertoireBdd.insertPersonne(perosnne);

        Toast.makeText(getApplicationContext(), perosnne.toString(), Toast.LENGTH_SHORT).show();
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
        NameEd.setText("");
        PrenomEd.setText("");
        TelEd.setText("");
        EmailEd.setText("");
        AddresseEd.setText("");
        CommentaireEd.setText("");
    }
}

package com.example.abk.tps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

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

        /* création d'une instance de la classe RepertoireBDD */
         RepertoireBDD repertoireBdd = new RepertoireBDD(this);

        /* on supprimer tout les espaces dans la saisie de Tél d'utilisateur*/
        String tel = TelEd.getText().toString().replaceAll(" ","");

        /* on créer un Objet Personne à partir les information saisie par utilisateur*/
        Personne perosnne = new Personne(NameEd.getText().toString(),PrenomEd.getText().toString(),
                tel,EmailEd.getText().toString(),AddresseEd.getText().toString(),
                CommentaireEd.getText().toString());

        /* on ouvre la base de données pour écrire dedans */

        /* Ouverture la base de données */
/************ repertoireBdd.open();*/


        /* verification de la saisie */
        String add[] = EmailEd.getText().toString().split("@");
        /* on vérifier si utilisateur à bien saisie son address Email */
        if(!EmailEd.getText().toString().contains("@") || add.length < 2 || !add[1].contains(".")){
            Toast.makeText(getApplicationContext(), "veuillez saisie une bonne addresse Email ... ", Toast.LENGTH_SHORT).show();

        }/* on véifier si le Numéro de Tél est correct  */
        else if( !tel.startsWith("0") && !tel.startsWith("+")){
            Toast.makeText(getApplicationContext(), "veuillez saisie un bonne Numéro de Tél .... ", Toast.LENGTH_SHORT).show();

        }else{
            /* on insert dans notre Base de données */
/****************** repertoireBdd.insertPersonne(perosnne);*/
            /* on férme notre BDD */
/************* repertoireBdd.close(); */
            /* aprés on a vérifier l'address Email et le Tél d'utilisateur et on a insérer notre Objet Personne
             * créer dans notre base de données on férem l'activity et on revient aù notre menu */
            finish();
        }

    }

    /* afficher des notification */
    void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    /* videe les champs de saisie */
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

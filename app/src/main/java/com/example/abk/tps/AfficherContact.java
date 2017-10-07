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
 * Created by abk on 30/09/2017.
 */

public class AfficherContact extends AppCompatActivity {


        private TextView Name,Prenom,Tel,Email,Adress,Commentaire;
        private int id;
        private RepertoireBDD repertoireBDD;

    @Override
    public void onResume(){
        super.onResume();
        actualiser();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        repertoireBDD.close();
    }

    @Override
    public void onStart()
    {
        super.onStart();
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

        /* on ouvre la base de données pour écrire dedans */
        //repertoireBDD.open();

        /* on insér le contact dans la base de données */

        Intent intent = getIntent();
        int position = Integer.parseInt(intent.getStringExtra("numero"));
        id = position;
        //actualiser();
        /*
        //Toast.makeText(getApplicationContext(), "position : "+position, Toast.LENGTH_SHORT).show();
        Personne p = repertoireBDD.getPersonneById(position+1);
        repertoireBDD.close();

        Name.setText(p.getName());
        Prenom.setText(p.getPrenom());
        Tel.setText(p.getTel());
        Email.setText(p.getEmail());
        Adress.setText(p.getAddress());
        Commentaire.setText(p.getCommentaire());*/

    }

    public void actualiser(){
        Personne p = repertoireBDD.getPersonneById(id);


        Name.setText(p.getName());
        Prenom.setText(p.getPrenom());
        Tel.setText(p.getTel());
        Email.setText(p.getEmail());
        Adress.setText(p.getAddress());
        Commentaire.setText(p.getCommentaire());
    }

    public void Annuler(View v){
        finish();
    }

    public void Modifier(View v){

        Intent intent = new Intent(AfficherContact.this, ModifierContact.class);
        intent.putExtra("id",""+id);
        startActivity(intent);
    }
}

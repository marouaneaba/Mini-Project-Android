package com.example.abk.tps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Marouane Abakarim on 30/09/2017.
 */

public class ModifierContact extends AppCompatActivity {



    private EditText EName,EPrenom,ETel,EEmail,EAddress,ECommentaire;
    private String Name,Prenom,Tel,Email,Address,Commentaire;
    private RepertoireBDD repertoireBDD;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier);

        Intent intent = getIntent();
        position = Integer.parseInt(intent.getStringExtra("id"));

        EName = (EditText)findViewById(R.id.EName);
        EPrenom = (EditText)findViewById(R.id.EPrenom);
        ETel = (EditText)findViewById(R.id.ETel);
        EEmail = (EditText)findViewById(R.id.EEmail);
        EAddress = (EditText)findViewById(R.id.EAddress);
        ECommentaire = (EditText)findViewById(R.id.ECommentaire);

        repertoireBDD = new RepertoireBDD(this);
        repertoireBDD.open();
        Personne p = repertoireBDD.getPersonneById(position);
        EName.setText(p.getName());
        EPrenom.setText(p.getPrenom());
        ETel.setText(p.getTel());
        EEmail.setText(p.getEmail());
        EAddress.setText(p.getAddress());
        ECommentaire.setText(p.getCommentaire());

        if(p == null)
            Toast.makeText(getApplicationContext(), "Null ,position : "+position, Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(), "Not Null ,position : "+position, Toast.LENGTH_SHORT).show();

    }

    public void Eregistrer(View v)
    {
        //repertoireBDD.open();
        Personne p = new Personne(EName.getText().toString(),EPrenom.getText().toString(),
                                    ETel.getText().toString().replaceAll(" ",""),EEmail.getText().toString(),
                                    EAddress.getText().toString(),ECommentaire.getText().toString());

        repertoireBDD.updateById(position,p);
        //repertoireBDD.close();
        finish();
    }

    public void Annuler(View v)
    {
        finish();
    }
}

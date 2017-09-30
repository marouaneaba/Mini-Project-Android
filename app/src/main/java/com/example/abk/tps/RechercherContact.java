package com.example.abk.tps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by abk on 01/10/2017.
 */

public class RechercherContact extends AppCompatActivity {


    private RepertoireBDD repertoireBDD;
    private ListView listView;
    private TextView textView;
    private EditText editTexte;

    private String texte;

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


    }

    public void Rechercher(View v)
    {
            int nbr = repertoireBDD.getPersonneByOther("ae",null,null,null,null,null).getCount();
        Toast.makeText(getApplicationContext(), "le nombre : "+nbr, Toast.LENGTH_SHORT).show();
    }
}

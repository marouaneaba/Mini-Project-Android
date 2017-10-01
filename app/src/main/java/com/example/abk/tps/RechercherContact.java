package com.example.abk.tps;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

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
            int nbr = repertoireBDD.getPersonneByOther(editTexte.getText().toString()+"%",editTexte.getText().toString()+"%",
                                                editTexte.getText().toString()+"%",editTexte.getText().toString()+"%",
                                                editTexte.getText().toString()+"%",editTexte.getText().toString()+"%").getCount();

        Cursor c = repertoireBDD.getPersonneByOther(editTexte.getText().toString()+"%",editTexte.getText().toString()+"%",
                editTexte.getText().toString()+"%",editTexte.getText().toString()+"%",
                editTexte.getText().toString()+"%",editTexte.getText().toString()+"%");


        List<Map<String, String>> list = repertoireBDD.CursorToVectorPersonne(c);

        // Cree un adapter faisant le lien entre la liste d'élément et la ListView servant à l'affichage.
        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), list, R.layout.personne_detail,
                new String[] {"Name","Prenom","Email"},
                new int[] { R.id.Name,R.id.Prenom, R.id.Email});
        //Associe l’adapter et le ListView
        listView.setAdapter(listAdapter);

        Toast.makeText(getApplicationContext(), "le nombre : "+nbr, Toast.LENGTH_SHORT).show();
    }
}

package com.example.abk.tps;

/**
 * Created by abk on 30/09/2017.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.DialogFragment;
import android.app.*;
import android.content.*;
import android.widget.*;

public class DFragement extends DialogFragment {



    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialogfragment,null);



        setCancelable(true);
        return view;
    }

    public void Add(View v){
        Intent in = new Intent(getActivity(),ModifierContact.class);
        startActivity(in);
    }

    public void Delete(View v){

    }
}

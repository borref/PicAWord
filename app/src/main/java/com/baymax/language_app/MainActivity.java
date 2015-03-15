package com.baymax.language_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.baymax.language_app.Model.DBAdapter;

import java.util.ArrayList;


public class MainActivity extends Activity {

    DBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new DBAdapter(this);

        if(adapter.getAllDataLevel().size() == 0){
            adapter.insertDataLevel("hola");
            //Message.message(this, "estoy vacio");
        }else{
            //Message.message(this, "estoy con info");
        }

        ArrayList<String> data = adapter.getAllDataLevel();

        for (int i = 0; i < data.size(); i++) {
            //Message.message(this, data.get(i));
        }
    }

    public void nextActivity(View view){
        startActivity(new Intent(this, ChooseLevel.class));
        finish();
    }
}

package com.baymax.language_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baymax.language_app.Model.DBAdapter;


public class ChooseLevel extends Activity {

    DBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        adapter = new DBAdapter(this);

    }

    public void nextActivity(View view){
        startActivity(new Intent(this, Categories.class));
    }
}

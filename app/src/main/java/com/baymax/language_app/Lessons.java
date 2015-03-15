package com.baymax.language_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class Lessons extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

    }

    public void goFlashcards(View view){
        startActivity(new Intent(this, Flashcards.class));
    }

}

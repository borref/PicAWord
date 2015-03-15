package com.baymax.language_app;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Flashcards extends FragmentActivity {

    ViewPager pager = null;
    MyFragmentPageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        this.setContentView(R.layout.activity_flashcards);

        this.pager = (ViewPager) this.findViewById(R.id.pager);

        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.material_blue_grey_800), 0));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.material_blue_grey_950), 1));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.material_deep_teal_500), 2));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.material_blue_grey_900), 3));
        this.pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flashcards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goDrawing(View view){
        startActivity(new Intent(this, DrawingCanvas.class));
    }
}

package com.epicodus.athletetracker.ui;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;


import android.support.v4.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.epicodus.athletetracker.Models.News;
import com.epicodus.athletetracker.Services.NewsService;
import com.epicodus.athletetracker.ui.fragments.AboutAppFragment;
import com.epicodus.athletetracker.ui.fragments.BioFragment;
import com.epicodus.athletetracker.ui.fragments.ContactFragment;
import com.epicodus.athletetracker.R;


import org.parceler.Parcels;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WelcomePage2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        @Bind(R.id.toolbar) Toolbar mTools;
        @Bind(R.id.welcomePageHeader) TextView mWelcomePage;
        ArrayList<News> mNews = new ArrayList<>();

        @Bind(R.id.nav_view) NavigationView navigationView;

    public void clearFunction(){

        mWelcomePage.setText("");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        ButterKnife.bind(this);


//        Intent newsIntent = new Intent(WelcomePage2.this, NewsService.class);
//        startActivity(newsIntent);

        Typeface welcomePageFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        mWelcomePage.setTypeface(welcomePageFont);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        mWelcomePage.setText("Welcome, " + name + "!");
        setSupportActionBar(mTools);

//        mNews = Parcels.unwrap(getIntent().getParcelableExtra("news"));
//        int startingPosition = getIntent().getIntExtra("position", 0);






// DON'T DELETE YET
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mTools, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.name_on_Nav);
        TextView nav_email = (TextView)hView.findViewById(R.id.email_on_Nav);
        nav_user.setText(name);
        nav_email.setText(email);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_page2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.About) {
            clearFunction();
            setTitle("About the Application");
            AboutAppFragment fragment = new AboutAppFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "About the Application");
            fragmentTransaction.commit();
        } else if (id == R.id.Home) {
            //Rework later... kind of badly done, but works
           Intent intent = new Intent(this, WelcomePage2.class);
           startActivity(intent);

        } else if (id == R.id.Contact) {
            clearFunction();
            setTitle("Contact Us");
            ContactFragment fragment1 = new ContactFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment1, "Contact Us");
            fragmentTransaction.commit();

        } else if (id == R.id.Bio) {
            clearFunction();
            setTitle("About Us");
            BioFragment fragment2 = new BioFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment2, "About Us");
            fragmentTransaction.commit();
        } else if (id == R.id.Workout) {
            clearFunction();
            setTitle("My Workouts");
            Intent WorkoutIntent = new Intent(WelcomePage2.this, WorkoutActivity.class);
            startActivity(WorkoutIntent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

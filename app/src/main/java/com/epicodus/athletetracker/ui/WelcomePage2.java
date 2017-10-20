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

import com.epicodus.athletetracker.ui.fragments.AboutAppFragment;
import com.epicodus.athletetracker.ui.fragments.BioFragment;
import com.epicodus.athletetracker.ui.fragments.ContactFragment;
import com.epicodus.athletetracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WelcomePage2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) Toolbar mTools;
    @Bind(R.id.welcome_page) TextView mWelcomePage;
    @Bind(R.id.story_list_view) ListView mWorkoutList;
    @Bind(R.id.news_about_health) TextView mNewsAboutHealth;
    @Bind(R.id.nav_view) NavigationView navigationView;


//    private String [] stories = new String []{"I want there to be an API that populates stories about health here","DNA study provides insight into how to live longer","Child and teen obesity spreading across the globe"};
//    private String [] url = new String [] {"xxxx","http://www.bbc.com/news/health-41588613","http://www.bbc.com/news/health-41550159"};


    public void clearFunction(){
        mWorkoutList.setAdapter(null);
        mWelcomePage.setText("");
        mNewsAboutHealth.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        ButterKnife.bind(this);

        Typeface welcomePageFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        mWelcomePage.setTypeface(welcomePageFont);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        mWelcomePage.setText("Welcome, " + name + "!");
        setSupportActionBar(mTools);

    //ADAPTER



//        WorkoutAdapter adapter = new WorkoutAdapter(this, android.R.layout.simple_list_item_1, stories, url);
//        mWorkoutList.setAdapter(adapter);
//        mWorkoutList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//                String workouts = ((TextView)view).getText().toString();
//                Toast.makeText(WelcomePage2.this, workouts, Toast.LENGTH_LONG).show();
//
//            }
//        });

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


//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Handle the navigation if clicked, told which fragment to go to.
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
            Intent WorkoutIntent = new Intent(WelcomePage2.this, WorkoutDetailActivity.class);
            startActivity(WorkoutIntent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

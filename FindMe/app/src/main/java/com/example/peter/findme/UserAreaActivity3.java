package com.example.peter.findme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class UserAreaActivity3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // take care of the mailbox button
                Intent intent = new Intent(UserAreaActivity3.this, MapsActivity.class);
                UserAreaActivity3.this.startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.user_area_activity3, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the logout action
            Intent intent = new Intent(UserAreaActivity3.this, LoginActivity.class);
            UserAreaActivity3.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(UserAreaActivity3.this, UserAreaActivity.class);
            // to get parameter from Intent
            String name = getIntent().getStringExtra("name");
            String email = getIntent().getStringExtra("email");
            String userName  = getIntent().getStringExtra("username");
            // to pass parameter
            intent.putExtra("name", name);
            intent.putExtra("username", userName);
            intent.putExtra("email", email);
            UserAreaActivity3.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            /*Intent intent = new Intent(UserAreaActivity3.this, MessageActivity.class);
            UserAreaActivity3.this.startActivity(intent);*/

            Intent loadingActivity = new Intent(UserAreaActivity3.this, LoadingActivity.class);
            loadingActivity.putExtra("username",getIntent().getStringExtra("username"));
            UserAreaActivity3.this.startActivity(loadingActivity);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(UserAreaActivity3.this, Setting.class);
            UserAreaActivity3.this.startActivity(intent);
        } else if (id == R.id.nav_share) {
            boolean shareLocation = true;
            Intent intent = new Intent(UserAreaActivity3.this, MapsActivity.class);
            intent.putExtra("share", shareLocation);
            UserAreaActivity3.this.startActivity(intent);
        } else if (id == R.id.nav_send) {
            boolean shareLocation = false;
            Intent intent = new Intent(UserAreaActivity3.this, MapsActivity.class);
            intent.putExtra("share", shareLocation);
            UserAreaActivity3.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

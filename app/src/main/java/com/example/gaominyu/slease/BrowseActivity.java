package com.example.gaominyu.slease;

        import android.app.Fragment;
        import android.content.Intent;
        import android.graphics.Color;
        import android.support.annotation.NonNull;
        import android.support.design.internal.BottomNavigationMenu;
        import android.support.design.internal.BottomNavigationMenuView;
        import android.support.design.widget.BottomNavigationView;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.SearchView;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

        import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
        import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class BrowseActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // Set up top bar
        Toolbar topToolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(topToolbar);

        // Set up floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrowseActivity.this, PhotoActivity.class));
            }
        });

        //Set up Bottom Navigation Bar at bottom
        toolbar = getSupportActionBar();
        initBottomNavBar();
        toolbar.setTitle("Shop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Set up the menu options on top tool bar.
        getMenuInflater().inflate(R.menu.directory, menu);

        MenuItem searchItem = menu.findItem(R.id.action_searchs);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        // TO DO
        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_searchs:
                // User chose the "Search" action, activate text input and search button
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    protected void initBottomNavBar() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_browse:
                    toolbar.setTitle("Browse");
                    return true;
                case R.id.navigation_activity:
                    toolbar.setTitle("Activity");
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("My Profile");
                    return true;
            }
            return false;
        }
    };
}

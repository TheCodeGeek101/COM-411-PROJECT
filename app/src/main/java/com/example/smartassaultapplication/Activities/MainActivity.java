package com.example.smartassaultapplication.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.smartassaultapplication.Fragments.HomeFragment;
import com.example.smartassaultapplication.Fragments.LocationFragment;
import com.example.smartassaultapplication.Fragments.ProfileFragment;
import com.example.smartassaultapplication.Fragments.WeatherFragment;
import com.example.smartassaultapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

        private FirebaseAuth smartAssaultAuth;
        private DatabaseReference mDatabase;
         BottomNavigationView bottomNavigationView;
//        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
    private RelativeLayout layout;
    private DrawerLayout drawerLayout;
//    public ActionBarDrawerToggle actionBarDrawerToggle;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.nav_host_fragment);
//
//            NavController navController = navHostFragment.getNavController();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph())
                            .setDrawerLayout(drawerLayout)
                            .build();

            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar == null) {
                // toolbar is null, so do something to handle this

            } else {
                // toolbar is not null, so it is safe to call setupWithNavController
                NavigationUI.setupWithNavController(toolbar, navController,appBarConfiguration);
            }
            NavigationUI.setupWithNavController(
                    toolbar, navController, appBarConfiguration);


//  layout = (RelativeLayout) findViewById(R.id.layout);
//    configureNavigationDrawer();
//    configureToolbar();

//            Toolbar toolbar = findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//
//            DrawerLayout drawer = findViewById(R.id.drawerLayout);
//            ActionBar actionBar = getSupportActionBar();
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
//
//            NavigationView navigationView = findViewById(R.id.nav_drawer);
//            navigationView.setNavigationItemSelectedListener(
//                    new NavigationView.OnNavigationItemSelectedListener() {
//                        @Override
//                        public boolean onNavigationItemSelected(MenuItem menuItem) {
//                            // set item as selected to persist highlight
//                            menuItem.setChecked(true);
//                            // close drawer when item is tapped
//                            drawer.closeDrawers();
//
//                            // Add code here to update the UI based on the item selected
//                            // For example, swap UI fragments here
//
//                            return true;
//                        }
//                    });
//
//            .setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    drawer.openDrawer(GravityCompat.START);
//                }
//            });

            bottomNavigationView = findViewById(R.id.btnNav);
//
        fragmentReplace(new HomeFragment());


        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    fragmentReplace(new HomeFragment());
                    break;
                case R.id.weather:
                    fragmentReplace(new WeatherFragment());
                    break;
                case R.id.maps:
                    fragmentReplace(new LocationFragment());
                    break;
                case R.id.profile:
                    fragmentReplace(new ProfileFragment());
                    break;
            }
            return true;
        });
        smartAssaultAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = smartAssaultAuth.getCurrentUser();
//        TextView tvLogout = findViewById(R.id.signout);
//        tvLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logoutUser();
//            }
//        });

// ...
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference reference = firebaseDatabase.getReference("users").child(currentUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Soldier soldier = snapshot.getValue(Soldier.class);
//                if(soldier != null)
//                {
//                    textFullname.setText("Fullname:" + soldier.fullname);
//                    textEmail.setText("Email Address:" + soldier.email);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void configureToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);
//        actionbar.setDisplayHomeAsUpEnabled(true);
    }
    private void configureNavigationDrawer() {
//        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
//        NavigationView navView = (NavigationView) findViewById(R.id.nav_drawer);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                Fragment f = null;
//                int itemId = menuItem.getItemId();
//                if (itemId == R.id.nav_catalog) {
//                    f = new WeatherFragment();
//                } else if (itemId == R.id.nav_Duties) {
//                    f = new ProfileFragment();
//                }        if (f != null) {
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame, f);
//                    transaction.commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                return false;
//            }
//        });

//        NavigationView navView = findViewById(R.id.nav_drawer);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                // code to handle menu item selection
//                return true;
//            }
//        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();    switch(itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;      // manage other entries if you have it ...
        }    return true;
    }

//
//    static void openDrawer(DrawerLayout drawerLayout) {
//            drawerLayout.openDrawer(GravityCompat.START);
//    }
//
//
//
//    public static void closeDrawer(DrawerLayout drawerLayout) {
//            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START);
//            }
//
//        }
//        public void clickSettings()
//        {
//            Intent intent = new Intent(this,Settings.class);
//            startActivity(intent);
//        }
//
//    public void redirectActivity(Activity activity, Class Class) {
//            Intent intent = new Intent(activity,Class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        }

//    public void clickLogout(View view)
//        {
////            logoutUser(this);
//        }

//    public static void logoutUser(Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("Logout");
//        builder.setMessage("Are you sure you want to log out?");
//        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
//            FirebaseAuth.getInstance().signOut();
////            Intent intent = new Intent(this,LoginActivity.class);
////            startActivity(intent);
//            activity.finishAffinity();
//            System.exit(0);
//        });
//        builder.setNegativeButton("No", (dialogInterface, i) -> {
//           dialogInterface.dismiss();
//        });
//        builder.show();
//    }
//
//    @Override
//    protected void onPause() {
//            super.onPause();
//            closeDrawer(drawerLayout);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void fragmentReplace(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
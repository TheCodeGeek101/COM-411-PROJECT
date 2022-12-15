package com.example.smartassaultapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

        private FirebaseAuth smartAssaultAuth;
        private DatabaseReference mDatabase;
         BottomNavigationView bottomNavigationView;
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;

//    public ActionBarDrawerToggle actionBarDrawerToggle;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
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
                    fragmentReplace(new MapsFragment());
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
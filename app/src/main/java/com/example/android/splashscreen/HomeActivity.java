package com.example.android.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.android.splashscreen.RecycleViewBackEnd.Home_RV.Name;
import com.example.android.splashscreen.RecycleViewBackEnd.Home_RV.NameAdapter;
import com.example.android.splashscreen.ui.bookmark.BookmarkFragment;
import com.example.android.splashscreen.ui.books.BooksFragment;
import com.example.android.splashscreen.ui.buybooks.BuybooksFragment;
import com.example.android.splashscreen.ui.home.HomeFragment;
import com.example.android.splashscreen.ui.setting.SettingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationViewCard;
    Toolbar toolbar;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello from other side", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationViewCard = findViewById(R.id.navAction);
        navigationViewCard.setNavigationItemSelectedListener(this);
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
     /*   mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_books, R.id.nav_Bookmark, R.id.nav_Settings,R.id.nav_BuyBooks)
                .setOpenableLayout(drawerLayout).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationViewCard, navController);*/
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container, new HomeFragment()).commit();
            navigationViewCard.setCheckedItem(R.id.navHome);
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new HomeFragment()).commit();
                break;
            case R.id.navBooks:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new BooksFragment()).commit();
                break;
            case R.id.navBookmark:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new BookmarkFragment()).commit();
                break;
            case R.id.navSetting:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new SettingFragment()).commit();
                break;
            case R.id.navBuy:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,new BuybooksFragment()).commit();
                break;
            default:
                System.out.println("ERROR");
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.ProfileItem)
        {
            Intent intent = new Intent(HomeActivity.this , ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moveTaskToBack(true);
    }
}
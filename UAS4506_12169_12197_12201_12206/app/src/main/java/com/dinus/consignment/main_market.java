package com.dinus.consignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class main_market extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     //Variable drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Boolean tombola;
    public static final String tombol = "utkTombol";

    SharedPreferences sharedPreferences;
    public static final String SHARED = "1";

    SliderView sliderView;

    Button btnCart;
    ImageButton btnKeyboard, btnMouse, btnMonitor, btnKomputer, btnKomponen, btnHeadphone, btnItem,
            btnTop_up;


    int[] images = {R.drawable.ongkir,
            R.drawable.promo,
            R.drawable.promo2, R.drawable.promo3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_market);

        //Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar);



        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        tombola = sharedPreferences.getBoolean(tombol, false);


        //Hide os show item
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(tombola);
        menu.findItem(R.id.nav_profile).setVisible(tombola);
        menu.findItem(R.id.nav_login).setVisible(!tombola);
        menu.findItem(R.id.nav_history).setVisible(tombola);




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


        sliderView = findViewById(R.id.image_slide);
        btnCart = findViewById(R.id.cart);
        btnKeyboard = findViewById(R.id.keyboard);
        btnMouse = findViewById(R.id.mouse);
        btnMonitor = findViewById(R.id.monitor);
        btnKomputer = findViewById(R.id.komputer);
        btnKomponen = findViewById(R.id.komponen);
        btnHeadphone = findViewById(R.id.headphone);
        btnItem = findViewById(R.id.item);
        btnTop_up = findViewById(R.id.top_up);

        //Kondisi sebelum dan sesudah login
        if(tombola == true){
            btnCart.setVisibility(View.VISIBLE);
        }else{
            btnCart.setVisibility(View.INVISIBLE);
        }


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, cart_shop.class);
                startActivity(i);
            }
        });

        btnKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_keyboard.class);
                startActivity(i);
            }
        });

        btnMouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_mouse.class);
                startActivity(i);
            }
        });

        btnMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_monitor.class);
                startActivity(i);
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_item.class);
                startActivity(i);
            }
        });

        btnKomponen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_komponen.class);
                startActivity(i);
            }
        });

        btnKomputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_komputer.class);
                startActivity(i);
            }
        });

        btnHeadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_headphone.class);
                startActivity(i);
            }
        });

        btnTop_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_market.this, market_topUp.class);
                startActivity(i);
            }
        });

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = navigationView.getMenu();
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_login:
                Intent i = new Intent(main_market.this, Login.class);
                startActivity(i);
                menu.findItem(R.id.nav_logout).setVisible(tombola);
                menu.findItem(R.id.nav_profile).setVisible(tombola);
                menu.findItem(R.id.nav_history).setVisible(tombola);
                menu.findItem(R.id.nav_login).setVisible(!tombola);
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                menu.findItem(R.id.nav_history).setVisible(!tombola);
                menu.findItem(R.id.nav_logout).setVisible(!tombola);
                menu.findItem(R.id.nav_profile).setVisible(!tombola);
                menu.findItem(R.id.nav_login).setVisible(tombola);
                finish();
                break;
            case R.id.nav_history:
                Intent h = new Intent(main_market.this, riwayat.class);
                startActivity(h);
                break;
            case R.id.nav_location:
                Intent u = new Intent(main_market.this, Lokasi.class);
                startActivity(u);
                break;
            case R.id.nav_about:
                Intent j = new Intent(main_market.this, about_us.class);
                startActivity(j);
                break;
            case R.id.nav_rate:
                Intent com = new Intent(main_market.this, coment_apps.class);
                startActivity(com);
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
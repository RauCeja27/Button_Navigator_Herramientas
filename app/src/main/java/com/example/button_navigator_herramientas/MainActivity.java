package com.example.button_navigator_herramientas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.button_navigator_herramientas.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    FragmentLinterna linternaFragment = new FragmentLinterna();
    FragmentMusica musicaFragment = new FragmentMusica();
    FragmentNivel nivelFragment = new FragmentNivel();
    //FragmentInicio inicioFragment = new FragmentInicio();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.menu1);
        bottomNavigationView.setItemIconTintList(null);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,inicioFragment).commit();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.menu1.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.itmLinterna){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, linternaFragment).commit();
                //Toast.makeText(MainActivity.this, "Home Fragment", Toast.LENGTH_SHORT).show();
                return true;
            }

            if(item.getItemId() == R.id.itmMusica){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, musicaFragment).commit();
                //Toast.makeText(MainActivity.this, "Profile Fragment", Toast.LENGTH_SHORT).show();
                return true;
            }

            if(item.getItemId() == R.id.itmNivel){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, nivelFragment).commit();
                //Toast.makeText(MainActivity.this, "Settings Fragment", Toast.LENGTH_SHORT).show();
                return true;
            }

            return true;
        });


    }
}
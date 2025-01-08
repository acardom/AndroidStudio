package com.dam.multilenguaje;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);

            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFC0CB")));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id==R.id.item_guardar){
            Toast.makeText(this, "guardado", Toast.LENGTH_SHORT).show();
        }else if (id==R.id.item_eliminar){
            Toast.makeText(this, "borrado", Toast.LENGTH_SHORT).show();
        }else if (id==R.id.item_salir){
            Toast.makeText(this, "saliendo", Toast.LENGTH_SHORT).show();
        }
        return  super.onOptionsItemSelected(item);
    }
}
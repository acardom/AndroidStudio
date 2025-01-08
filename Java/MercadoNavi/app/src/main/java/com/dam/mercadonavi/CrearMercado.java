package com.dam.mercadonavi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CrearMercado extends AppCompatActivity {

    EditText et_id, et_nombre, et_ubi, et_fin, et_ini;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_mercado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_id = findViewById(R.id.et_id);
        et_nombre = findViewById(R.id.et_nombre);
        et_ubi = findViewById(R.id.et_ubi);
        et_ini = findViewById(R.id.et_ini);
        et_fin = findViewById(R.id.et_fin);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EA0000")));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu2){
        getMenuInflater().inflate(R.menu.menu2, menu2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id==R.id.VolverIni){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Bienvenido al inicio", Toast.LENGTH_SHORT).show();
        }
        return  super.onOptionsItemSelected(item);
    }

    public void CrearMercado(View v) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/MercadoNavi/crear.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
                et_fin.setText("");
                et_id.setText("");
                et_ini.setText("");
                et_ubi.setText("");
                et_nombre.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                // Estas son las $variables del microservicio
                if (!et_fin.getText().toString().isEmpty() && !et_id.getText().toString().isEmpty() && !et_ini.getText().toString().isEmpty() && !et_ubi.getText().toString().isEmpty() && !et_nombre.getText().toString().isEmpty()) {
                    parametros.put("id", et_id.getText().toString());
                    parametros.put("nombre", et_nombre.getText().toString());
                    parametros.put("ubicacion", et_ubi.getText().toString());
                    parametros.put("fecha_ini", et_ini.getText().toString());
                    parametros.put("fecha_fin", et_fin.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }

                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
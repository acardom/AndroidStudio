package com.dam.listadoalumnos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres[] = {"Antonio", "Alberto", "Jose Luis", "Lola", "Carlos", "Diego", "Julio", "Maria", "Antonio", "Alberto", "Jose Luis", "Lola", "Carlos", "Diego", "Julio", "Maria"};
    private String notas[] = {"3.5", "9.2", "7.7", "8", "No presentado", "6", "4.3", "10", "3.5", "9.2", "7.7", "8", "No presentado", "6", "4.3", "10"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        tv1 = findViewById(R.id.tv1);
        lv1 = findViewById(R.id.lv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.estilo_lista, nombres);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("La nota de "+ lv1.getItemAtPosition(i)+" es "+ notas[i]);
            }
        });

    }

    public void mensaje_boton(View view){
        Toast.makeText(this, "2  DAM", Toast.LENGTH_LONG).show();
    }

}
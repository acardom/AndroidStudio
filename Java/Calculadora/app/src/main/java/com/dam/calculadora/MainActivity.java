package com.dam.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //declaramos valores
    private EditText et1;
    private EditText et2;
    private TextView tv1;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

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

        //casting de int a EditText
        et1 = findViewById(R.id.txt_number1);
        et2 = findViewById(R.id.txt_number2);
        tv1 = findViewById(R.id.txt_resultado);

        rb1 = findViewById(R.id.rb_sumar);
        rb2 = findViewById(R.id.rb_restar);
        rb3 = findViewById(R.id.rb_multiplicar);
        rb4 = findViewById(R.id.rb_dividir);

    }

    public void calcular (View view){
        //recogemos los valores en string
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        String resultado = "";

        int valor1_int = Integer.parseInt(valor1);
        int valor2_int = Integer.parseInt(valor2);

        if (rb1.isChecked()){
            int suma = valor1_int + valor2_int;
            resultado = String.valueOf(suma);
        }else if (rb2.isChecked()){
            int resta = valor1_int - valor2_int;
            resultado = String.valueOf(resta);
        }else if (rb3.isChecked()){
            int multiplicar = valor1_int * valor2_int;
            resultado = String.valueOf(multiplicar);
        }else if (rb4.isChecked()){
            if(valor2_int != 0){
                Double dividir = (valor1_int*1.0)/valor2_int;
                resultado = String.valueOf(Math.round(dividir * 10000) / 10000d);
            }else{
                resultado = " infinito ";
            }

        }

        tv1.setText(resultado);

    }

    /*

public void sumar(View view){
//try por si da fallo que no se cierre y haga lo que esta en el catch
try {
    //recogemos los valores en string
    String valor1 = et1.getText().toString();
    String valor2 = et2.getText().toString();
    //pasamos a entero de string
    int num1 = Integer.parseInt(valor1);
    int num2 = Integer.parseInt(valor2);
    //hacemos la operación
    int suma = num1+num2;
    //lo mostramos
    String result = String.valueOf(suma);
    tv1.setText(result);
}catch (Exception e){
    //mensaje emergente corto de error
    Toast.makeText(this,"rellene los 2 campos numericos", Toast.LENGTH_SHORT).show();
}
}

public void restar(View view){
//try por si da fallo que no se cierre y haga lo que esta en el catch
try {
    //recogemos los valores en string
    String valor1 = et1.getText().toString();
    String valor2 = et2.getText().toString();
    //pasamos a entero de string
    int num1 = Integer.parseInt(valor1);
    int num2 = Integer.parseInt(valor2);
    //hacemos la operación
    int suma = num1-num2;
    //lo mostramos
    String result = String.valueOf(suma);
    tv1.setText(result);
}catch (Exception e){
    //mensaje emergente corto de error
    Toast.makeText(this,"rellene los 2 campos numericos", Toast.LENGTH_SHORT).show();
}
}

public void multiplicar(View view){
//try por si da fallo que no se cierre y haga lo que esta en el catch
try {
    //recogemos los valores en string
    String valor1 = et1.getText().toString();
    String valor2 = et2.getText().toString();
    //pasamos a entero de string
    int num1 = Integer.parseInt(valor1);
    int num2 = Integer.parseInt(valor2);
    //hacemos la operación
    int suma = num1*num2;
    //lo mostramos
    String result = String.valueOf(suma);
    tv1.setText(result);
}catch (Exception e){
    //mensaje emergente corto de error
    Toast.makeText(this,"rellene los 2 campos numericos", Toast.LENGTH_SHORT).show();
}
}

public void dividir(View view){
//try por si da fallo que no se cierre y haga lo que esta en el catch
try {
    //recogemos los valores en string
    String valor1 = et1.getText().toString();
    String valor2 = et2.getText().toString();
    //pasamos a entero de string
    int num1 = Integer.parseInt(valor1);
    int num2 = Integer.parseInt(valor2);
    //hacemos la operación
    Double div = (num1*1.0)/num2;

    //lo mostramos con limite de 3 decimales
    String result = String.valueOf(String.format("%.3f", div));
    tv1.setText(result);
}catch (Exception e){
    //mensaje emergente corto de error
    Toast.makeText(this,"rellene los 2 campos numericos", Toast.LENGTH_SHORT).show();
}
}

     */

}
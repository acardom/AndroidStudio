package com.dam.camara_galeria_listar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView visor;
    ActivityResultLauncher<Intent> capturadorImagenes = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == Activity.RESULT_OK){
                        Bundle extras = o.getData().getExtras();
                        Bitmap imagen = (Bitmap) extras.get("data");

                        if (imagen != null){
                            visor.setImageBitmap(imagen);
                            guardarImagenEnGaleria(imagen);
                        }else{
                            Toast.makeText(MainActivity.this, "error al capturar la pantalla", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

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

        visor = findViewById(R.id.iv_visor);
    }

    public void camara (View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        capturadorImagenes.launch(intent);
    }

    public void listar (View view){
        setContentView(R.layout.activity_listar);
    }

    private void guardarImagenEnGaleria(Bitmap imagen) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "captura_" + System.currentTimeMillis() + ".jpg");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CamaraApp");

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try (OutputStream outputStream = getContentResolver().openOutputStream(uri)) {
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
        }
    }
}
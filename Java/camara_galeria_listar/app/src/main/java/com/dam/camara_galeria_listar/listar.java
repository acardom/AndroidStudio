// MainActivity.java
package com.dam.camara_galeria_listar;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.camara_galeria_listar.R;

import java.util.ArrayList;
import java.util.List;

public class listar extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 100;
    private RecyclerView recyclerView;
    private List<Uri> imageUris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        imageUris = new ArrayList<>();
        recyclerView.setAdapter(new RecyclerView.Adapter<ImageViewHolder>() {
            @NonNull
            @Override
            public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
                return new ImageViewHolder((ImageView) view);
            }

            @Override
            public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
                Uri currentUri = imageUris.get(position);
                holder.imageView.setImageURI(currentUri); // Usamos setImageURI() para cargar la imagen
            }

            @Override
            public int getItemCount() {
                return imageUris.size();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        } else {
            loadGalleryImages();
        }
    }

    private void loadGalleryImages() {
        Uri imagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID};

        try (Cursor cursor = getContentResolver().query(imagesUri, projection, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC")) {
            if (cursor != null) {
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumn);
                    Uri contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                    imageUris.add(contentUri);

                    Log.d("GalleryApp", "Image URI: " + contentUri.toString()); // Para depurar
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading images", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadGalleryImages();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}

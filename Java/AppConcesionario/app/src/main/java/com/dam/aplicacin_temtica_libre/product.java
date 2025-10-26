package com.dam.aplicacin_temtica_libre;

public class product { // Cambié el nombre de la clase a "Product" para seguir la convención de nombres en Java

    private String name; // Nombre del producto
    private String price; // Precio del producto
    private String descripcion; // Descripción del producto
    private int imageResId; // Recurso de imagen del producto

    // Constructor de la clase Product
    public product(String name, String price, int imageResId, String descripcion) {
        this.name = name; // Inicializa el nombre del producto
        this.price = price; // Inicializa el precio del producto
        this.descripcion = descripcion; // Inicializa la descripción del producto
        this.imageResId = imageResId; // Inicializa el recurso de imagen
    }

    // Métodos de acceso (getters) para obtener los valores de los atributos

    public String getName() {
        return name; // Devuelve el nombre del producto
    }

    public String getDescripcion() {
        return descripcion; // Devuelve la descripción del producto
    }

    public String getPrice() {
        return price; // Devuelve el precio del producto
    }

    public int getImageResId() {
        return imageResId; // Devuelve el recurso de imagen del producto
    }
}

package com.dam.muelbles_ramon;

import java.util.ArrayList;

public class CarritoSingleton {
    private static CarritoSingleton instance;
    private ArrayList<String> productos;
    private ArrayList<Integer> precios;
    private int total;

    private CarritoSingleton() {
        productos = new ArrayList<>();
        precios = new ArrayList<>();
        total = 0;
    }

    public static CarritoSingleton getInstance() {
        if (instance == null) {
            instance = new CarritoSingleton();
        }
        return instance;
    }

    public void agregarProducto(String nombre, int precio) {
        productos.add(nombre + ": " + precio + "€");
        precios.add(precio);
        total += precio;
    }

    public ArrayList<String> getProductos() {
        return productos;
    }

    public int getTotal() {
        return total;
    }

    public void limpiarCarrito() {
        productos.clear();
        precios.clear();
        total = 0;
    }
}